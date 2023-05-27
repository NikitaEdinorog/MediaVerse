package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comment, UUID> {
    @Query("select count(c) from Comment c where c.postId = ?1")
    Long countByPostId(UUID postId);



}
