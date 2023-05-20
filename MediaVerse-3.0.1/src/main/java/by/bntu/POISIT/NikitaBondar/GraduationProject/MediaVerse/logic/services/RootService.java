package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs.UserDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.MyProfileDTO;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Post;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RootService {

    @Autowired
    private PostsService postsService;

    public ServiceResponse<MyProfileDTO> getMyProfile(User user){

        log.log(Level.INFO,"[userId: {}] Get profile", user.getId());

        UserDTO userDTO = new UserDTO(user);

        ServiceResponse<List<Post>> postsResponse = postsService.getPostByUserId(user.getId());
        if (postsResponse.isError()){
            return new ServiceResponse.Builder<MyProfileDTO>().buildError(postsResponse.getServiceError());
        }

        List<Post> postList = postsResponse.getData();

        return new ServiceResponse.Builder<MyProfileDTO>().buildSuccess(new MyProfileDTO(userDTO, postList,
                "./images/%s/%s-avatar".formatted(userDTO.getId(), userDTO.getId())));
    }

}
