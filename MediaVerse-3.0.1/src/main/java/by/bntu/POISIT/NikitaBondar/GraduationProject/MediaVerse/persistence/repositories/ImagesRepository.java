package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.ImagesNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesNames, Long> {
    @Query("select i from ImagesNames i where i.postId = ?1")
    List<ImagesNames> findByPostId(UUID postId);
}
