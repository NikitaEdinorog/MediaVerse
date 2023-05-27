package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewPostDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.NewTagLists;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceError;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.*;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers.PostsManager;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.FileWriter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Log4j2
public class PostsService {

    @Autowired
    private PostsManager postsManager;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private  UserService userService;

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

    public ServiceResponse<List<Post>> getPostsByUserId(UUID id) {
        log.log(Level.INFO, "[userId: {}] Get list post", id);

        List<Post> postList = new ArrayList<>();
        try {
            postList = postsManager.getPostsByUser(id);
        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<List<Post>>().buildError(ServiceError.UNKNOWN);
        }
        for (Post post : postList) {

            ServiceResponse<Post> postServiceResponse = fillPost(post);
            if (postServiceResponse.isError()) {
                return new ServiceResponse.Builder<List<Post>>().buildError(ServiceError.UNKNOWN);
            }
            post.fill(postServiceResponse.getData());
        }

        return new ServiceResponse.Builder<List<Post>>().buildSuccess(postList);
    }


    private ServiceResponse<Post> fillPost(Post post){

        Post herePost = new Post(post);

        ServiceResponse<List<Tag>> tagListServiceResponse = setPostTags(herePost);
        if (tagListServiceResponse.isError()) {
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }
        herePost.setTags(tagListServiceResponse.getData());

        ServiceResponse<List<ImagesNames>> imagesNamesServiceResponse = setImages(herePost);
        if (imagesNamesServiceResponse.isError()) {
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }
        herePost.setImages(imagesNamesServiceResponse.getData());

        ServiceResponse<Long> numbderOfCommentsResponse =  commentsService.countCommentsOnPost(herePost.getId());
        if (numbderOfCommentsResponse.isError()){
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }
        herePost.setComments(numbderOfCommentsResponse.getData());


        try{
            Long numberOfLikesResponse = postsManager.countLikesOnPost(herePost.getId());
            herePost.setLikes(numberOfLikesResponse);
        } catch (Exception e){
            exceptionLog(e);
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }

        ServiceResponse<User> authorResponse = userService.getUserById(herePost.getAuthorId().toString());
        if (authorResponse.isError()){
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }
        herePost.setAuthorUsername(authorResponse.getData().getUsername());

        return new ServiceResponse.Builder<Post>().buildSuccess(herePost);

    }


    private ServiceResponse<List<ImagesNames>> setImages(Post post) {

        try {
            List<ImagesNames> imagesNames = postsManager.getImagesByPostId(post.getId());
            imagesNames.forEach(imagesName -> imagesName.setPath(
                    FileWriter.getUserPostsPathForFront(
                            post.getAuthorId().toString(),
                            post.getId().toString()) + imagesName.getImageName()));


            return new ServiceResponse.Builder<List<ImagesNames>>().buildSuccess(imagesNames);
        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<List<ImagesNames>>().buildError(ServiceError.UNKNOWN);
        }

    }


    private ServiceResponse<List<Tag>> setPostTags(Post post) {

        log.log(Level.INFO, "[postId: {}] Setting tags", post.getId());

        try {
            List<Tags_Posts> tagsPostsList = postsManager.getTagsPostsByPost(post.getId());
            List<Tag> tags = new ArrayList<>();
            for (Tags_Posts tagPost : tagsPostsList) {
                Optional<Tag> tag = postsManager.getTagById(tagPost.getTagId());
                tag.ifPresent(tags::add);
            }

            return new ServiceResponse.Builder<List<Tag>>().buildSuccess(tags);
        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<List<Tag>>().buildError(ServiceError.UNKNOWN);
        }
    }


    public ServiceResponse<Post> saveNewPost(NewPostDTO newPostDTO, User user) {

        Post newPost = new Post(newPostDTO.getDescription(), user.getId());
        ServiceResponse<List<String>> tagsFromDB = getAllTags();

        if (tagsFromDB.isError()) {
            return new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }


        ServiceResponse<Post> postServiceResponse = save(newPost, newPostDTO.getFiles(), user.getId(), tagsFromDB.getData(), newPostDTO.getTags());


        return postServiceResponse;
    }


    @Transactional
    public ServiceResponse<Post> save(Post newPost,
                                      List<MultipartFile> files,
                                      UUID userId,
                                      List<String> tagsFromDB,
                                      String[] tagLines) {

        ServiceResponse<Post> serviceResponse = null;

        try {
            postsManager.saveNewOrder(newPost);

            FileWriter fileWriter = new FileWriter();
            List<String> imageNames = fileWriter.writePostImages(userId.toString(), newPost.getId().toString(), files);

            List<ImagesNames> imagesNamesList = imageNames.stream().map(imageName -> (new ImagesNames(newPost.getId(), imageName))).toList();
            System.out.println(imagesNamesList.toString());

            postsManager.saveImages(imagesNamesList);

            NewTagLists newTagLists = buildNewTags_Posts(
                    tagsFromDB,
                    tagLines,
                    newPost.getId());

            postsManager.saveNewTagsPosts(newTagLists);

            serviceResponse = new ServiceResponse.Builder<Post>().buildSuccess(newPost);

        } catch (Exception e) {
            exceptionLog(e);
            serviceResponse = new ServiceResponse.Builder<Post>().buildError(ServiceError.UNKNOWN);
        }

        return serviceResponse;
    }


    public NewTagLists buildNewTags_Posts(List<String> tagNames, String[] tagsLines, UUID post) {

        NewTagLists newTagLists = new NewTagLists();

        for (String tagLine : tagsLines) {

            tagLine = tagLine.toLowerCase();

            int index = tagNames.indexOf(tagLine);

            if (index != -1) {
                newTagLists.getTagsPosts().add(new Tags_Posts(post, (long) index + 1));
            } else {
                newTagLists.getNewTags().add(new Tag(tagLine));
                newTagLists.getTagPostsToNewTags().add(new Tags_Posts(post));
            }
        }

        return newTagLists;
    }


    public ServiceResponse<List<String>> getAllTags() {

        try {

            List<String> tagList = postsManager.getAllTags().stream().map(Tag::getName).toList();
            return new ServiceResponse.Builder<List<String>>().buildSuccess(tagList);

        } catch (Exception e) {
            exceptionLog(e);
            return new ServiceResponse.Builder<List<String>>().buildError(ServiceError.UNKNOWN);
        }

    }

    private void exceptionLog(Exception e) {
        log.log(Level.ERROR, "Unexpected error: {} - {}", e.getClass().getSimpleName(), e.getMessage());
    }

}
