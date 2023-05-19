package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

  @Id
  @Column(name = "user_id")
  public UUID user;

  @Column(name = "token",unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  public TokenType tokenType = TokenType.BEARER;

  @Column(name = "revoked")
  public boolean revoked;

  @Column(name = "expired")
  public boolean expired;
}
