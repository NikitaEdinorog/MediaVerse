package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewUserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceError;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services.interfaces.UserServiceInterface;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Password;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Role;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers.UserManager;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
@Log4j2
public class UserService implements UserServiceInterface {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserManager userManager;


    public ServiceResponse<User> getUserByUsername(String username) {

        logger.info("[username: {}]Request database for user by username", username);
        ServiceResponse<User> UserServiceResponse;

        try {
            Optional<User> UserOptional = userManager.queryUserByUsername(username);
            UserServiceResponse = UserOptional.isEmpty() ?
                    new ServiceResponse.Builder<User>().buildError(ServiceError.NOT_FOUND) :
                    new ServiceResponse.Builder<User>().buildSuccess(UserOptional.get());
        } catch (RuntimeException e) {
            exceptionLog(e);
            UserServiceResponse = new ServiceResponse.Builder<User>().buildError(ServiceError.UNKNOWN);
        }

        logger.info("[username: {}] Response: {}", username, UserServiceResponse);
        return UserServiceResponse;

    }

    @Override
    public ServiceResponse<User> getUserById(String id) {
        logger.info("[UserId: {}]Request database for user by id", id);
        ServiceResponse<User> orderServiceResponse;

        try {
            Optional<User> orderOptional = userManager.queryUserById(UUID.fromString(id));
            orderServiceResponse = orderOptional.isEmpty() ?
                    new ServiceResponse.Builder<User>().buildError(ServiceError.NOT_FOUND) :
                    new ServiceResponse.Builder<User>().buildSuccess(orderOptional.get());
        } catch (RuntimeException e) {
            exceptionLog(e);
            orderServiceResponse = new ServiceResponse.Builder<User>().buildError(ServiceError.UNKNOWN);
        }

        logger.info("[UserId: {}] Response: {}", id, orderServiceResponse);
        return orderServiceResponse;
    }


    private void exceptionLog(Exception e) {
        log.log(Level.ERROR, "Unexpected error: {} - {}", e.getClass().getSimpleName(), e.getMessage());
    }
}
