package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.NewTagLists;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.ImagesNames;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tag;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tags_Posts;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Log4j2
public class PostsManager {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TagsPostsRepository tagsPostsRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private LikesRepository likesRepository;

    public Optional<Post> getPostById(UUID id){
        return postsRepository.findById(id);
    }

    public List<Post> getPostsByUser(UUID userId){
        return postsRepository.findByAuthorId(userId);
    }

    public List<Tags_Posts> getTagsPostsByPost(UUID postId){
        return tagsPostsRepository.findByPostId(postId);
    }

    public Optional<Tag> getTagById(Long tagId){
        return tagsRepository.findById(tagId);
    }

    public List<ImagesNames> getImagesByPostId(UUID id) {
        return imagesRepository.findByPostId(id);
    }

    public void saveNewOrder(Post newPost) {
        postsRepository.save(newPost);
    }

    public List<Tag> getAllTags() {
        return tagsRepository.findAll();
    }

    public void saveNewTagsPosts(NewTagLists newTagLists) {
        System.out.println(newTagLists);
        if (newTagLists.getNewTags().size() > 0){
            tagsRepository.saveAll(newTagLists.getNewTags());
            for (int i =0; i<newTagLists.getNewTags().size();i++){
                newTagLists.getTagPostsToNewTags().get(i).setTagId(newTagLists.getNewTags().get(i).getId());
            }
            tagsPostsRepository.saveAll(newTagLists.getTagPostsToNewTags());
        }
        tagsPostsRepository.saveAll(newTagLists.getTagsPosts());
    }

    public void saveImages(List<ImagesNames> imagesNamesList) {
        imagesRepository.saveAll(imagesNamesList);
    }

    public Long countLikesOnPost(UUID id) {
        return likesRepository.countById_PostId(id);
    }
}
