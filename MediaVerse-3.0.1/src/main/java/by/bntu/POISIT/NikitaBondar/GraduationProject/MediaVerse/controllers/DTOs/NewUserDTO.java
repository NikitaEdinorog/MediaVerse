package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserDTO {
    private String firstName;
    private String secondName;
    private String username;
    private String email;
    private String password;
}
