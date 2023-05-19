package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import liquibase.ui.UIServiceFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@Log4j2
public class RootController {

    @GetMapping(value = "my-profile")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal User user){

        log.log(Level.INFO, "{}",user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public String getRoot(){
        return "root";
    }

}
