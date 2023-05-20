package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tags_Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagsPostsRepository extends JpaRepository<Tags_Posts, Long> {
    @Query("select t from Tags_Posts t where t.postId = ?1")
    List<Tags_Posts> findByPostId(UUID postId);

    @Query("select t from Tags_Posts t where t.tagId = ?1")
    List<Tags_Posts> findByTagId(Long tagId);


}
