package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewUserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.UserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services.UserService;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam(name = "username") String username)
    {
        logger.info("[username: {}]Requested user by username", username);

        ServiceResponse<User> serviceResponse = userService.getUserByUsername(username);

        ResponseEntity responseEntity = serviceResponse.isSuccess() ?
                new ResponseEntity(new UserDTO(serviceResponse.getData()), HttpStatus.OK) :
                new ResponseEntity(serviceResponse.getServiceError().getStatus().name(), serviceResponse.getServiceError().getStatus());

        logger.info("Response: {}", responseEntity);

        return  responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id){

        logger.info("[UserId: {}]Requested user by id", id);
        ServiceResponse<User> serviceResponse = userService.getUserById(id);

        UserDTO response = serviceResponse.isSuccess() ?
                new UserDTO(serviceResponse.getData()) : null;

        HttpStatus httpStatus = serviceResponse.isSuccess() ? HttpStatus.OK :
                serviceResponse.getServiceError().getStatus();

        logger.info("[orderId: {}] Response: {}", id, (serviceResponse.isSuccess() ?
                response :
                "Error: %s".formatted(serviceResponse.getServiceError().getStatus().name())));

        return new ResponseEntity<>(response, httpStatus);

    }


}
