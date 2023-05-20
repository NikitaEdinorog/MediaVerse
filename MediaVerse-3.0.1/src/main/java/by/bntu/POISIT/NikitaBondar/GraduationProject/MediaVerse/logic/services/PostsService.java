package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceError;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.ImagesNames;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tag;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tags_Posts;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers.PostsManager;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class PostsService {

    @Autowired
    private PostsManager postsManager;

    public ServiceResponse<Post> getPostById(UUID id) {
        log.log(Level.INFO, "[postId: {}] Get post", id);

        Optional<Post> postOptional = Optional.empty();
        try {
            postOptional = postsManager.getPostById(id);
        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }

        ServiceResponse<Post> response = null;
        if (postOptional.isPresent()) {

            try {
                setPostTags(postOptional.get());
            } catch (Exception e) {
                exceptionLog(e);
                return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
            }
            response = new ServiceResponse.Builder<Post>().buildSuccess(postOptional.get());
        } else {
            response = new ServiceResponse.Builder<Post>().buildError(ServiceError.NOT_FOUND);
        }
        return response;
    }

    public ServiceResponse<List<Post>> getPostByUserId(UUID id) {
        log.log(Level.INFO, "[userId: {}] Get list post", id);

        List<Post> postList = new ArrayList<>();
        try {
            postList = postsManager.getPostsByUser(id);
            postList.stream().map(this::setPostTags);
            postList.stream().map(this::setImages);
        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<List<Post>>().buildError(ServiceError.UNKNOWN);
        }
        return new ServiceResponse.Builder<List<Post>>().buildSuccess(postList);
    }

    private Post setImages(Post post) {

        List<ImagesNames> imagesNames = postsManager.getImagesByPostId(post.getId());
        imagesNames.forEach(imagesName -> imagesName.setPath(
                "./images/%s/%s/%s".formatted(
                        post.getAuthorId(),
                        post.getId(),
                        imagesName.getImageName())));

        post.setImages(imagesNames);
        return post;

    }


    private Post setPostTags(Post post) {

        log.log(Level.INFO, "[postId: {}] Setting tabs", post.getId());

        List<Tags_Posts> tagsPostsList = postsManager.getTagsPostsByPost(post.getId());
        for (Tags_Posts tagPost : tagsPostsList) {
            Optional<Tag> tag = postsManager.getTagById(tagPost.getTagId());
            tag.ifPresent(value -> post.getTags().add(value));
        }

        return post;
    }


    private void exceptionLog(Exception e) {
        log.log(Level.ERROR, "Unexpected error: {} - {}", e.getClass().getSimpleName(), e.getMessage());
    }

}
