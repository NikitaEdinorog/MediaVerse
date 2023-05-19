package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;

    private String firstName;

    private String secondName;

    private String username;

    private String description;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.username = user.getUsername();
        this.description = user.getDescription();
    }

}
