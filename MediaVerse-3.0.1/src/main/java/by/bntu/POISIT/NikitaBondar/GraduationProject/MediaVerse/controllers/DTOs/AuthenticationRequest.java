package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  private String username;
  private String password;
}
