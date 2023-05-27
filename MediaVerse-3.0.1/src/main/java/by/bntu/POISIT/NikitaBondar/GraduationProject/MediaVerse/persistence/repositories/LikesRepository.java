package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LikesRepository extends JpaRepository<Like, UUID> {
    @Query("select count(l) from Like l where l.id.postId = ?1")
    Long countById_PostId(UUID postId);
}
