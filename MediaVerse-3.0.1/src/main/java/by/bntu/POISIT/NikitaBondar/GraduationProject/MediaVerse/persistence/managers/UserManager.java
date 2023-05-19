package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Password;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories.PasswordsRepository;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordsRepository passwordsRepository;

    @Transactional
    public void saveNewUser(User user){

        userRepository.save(user);

        Password passwordEnt = new Password(user.getId(), user.getPassword());

        passwordsRepository.save(passwordEnt);
    }


    public Optional<User> queryUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public Optional<Password> queryPassword(UUID id){
        return passwordsRepository.findById(id);
    }

    public Optional<User> queryUserById(UUID id) {
        return userRepository.findById(id);

    }
}
