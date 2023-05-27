package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.NewPostDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services.PostsService;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;


    @GetMapping("/new-post")
    public ResponseEntity getNewPost() {
        ServiceResponse<List<String>> tagNames = postsService.getAllTags();

        ResponseEntity response = tagNames.isSuccess() ?
                new ResponseEntity<List<String>>(tagNames.getData(), HttpStatus.OK):
                new ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR);

        log.log(Level.INFO, "Posts get");
        return response;
    }

    @PostMapping("/new-post")
    public ResponseEntity saveNewPost(@RequestParam(value = "file1") MultipartFile file1,
                                      @RequestParam(value = "file2", required = false) MultipartFile file2,
                                      @RequestParam(value = "file3", required = false) MultipartFile file3,
                                      @RequestParam(value = "file4", required = false) MultipartFile file4,
                                      @RequestParam(value = "file5", required = false) MultipartFile file5,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "tags") String[] tags,
                                      @AuthenticationPrincipal User user
    ) {


        NewPostDTO newPostDTO = new NewPostDTO(file1, file2, file3, file4, file5, description, tags);

        ServiceResponse<Post> postServiceResponse =  postsService.saveNewPost(newPostDTO, user);
        ResponseEntity<String> response = postServiceResponse.isSuccess() ?
                new ResponseEntity<>("succes", HttpStatus.CREATED) :
                new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);


        return response;
    }


}
