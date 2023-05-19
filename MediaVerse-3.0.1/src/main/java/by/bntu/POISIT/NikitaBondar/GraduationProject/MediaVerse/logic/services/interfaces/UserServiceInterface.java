package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services.interfaces;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewUserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface {


    ServiceResponse getUserByUsername(String username);

    ServiceResponse<User> getUserById(String id);
}
