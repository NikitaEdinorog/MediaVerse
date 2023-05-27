package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentsManager {

    @Autowired
    private CommentsRepository commentsRepository;

    public Long countCommentsOnPost(UUID postId) {
        return commentsRepository.countByPostId(postId);
    }
}
