package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PostsRepository extends JpaRepository<Post, UUID> {
    @Query("select p from Post p where p.authorId = ?1")
    List<Post> findByAuthorId(UUID authorId);
}
