package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.services;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceError;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs.ServiceResponse;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers.CommentsManager;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class CommentsService {

    @Autowired
    private CommentsManager commentsManager;

    public ServiceResponse<Long> countCommentsOnPost(UUID postId){

        try {
            Long numberOfCommentsOnPost = commentsManager.countCommentsOnPost(postId);
            return new ServiceResponse.Builder<Long>().buildSuccess(numberOfCommentsOnPost);
        } catch (Exception e){
            exceptionLog(e);
            return new ServiceResponse.Builder<Long>().buildError(ServiceError.UNKNOWN);
        }
    }


    private void exceptionLog(Exception e) {
        log.log(Level.ERROR, "Unexpected error: {} - {}", e.getClass().getSimpleName(), e.getMessage());
    }

}
