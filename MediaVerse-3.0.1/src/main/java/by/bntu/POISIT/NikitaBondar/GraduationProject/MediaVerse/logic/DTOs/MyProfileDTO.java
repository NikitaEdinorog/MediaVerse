package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.UserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileDTO {

    private UserDTO user;
    private List<Post> posts;
    private String userImagePath;



}
