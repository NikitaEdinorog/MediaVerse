package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

}
