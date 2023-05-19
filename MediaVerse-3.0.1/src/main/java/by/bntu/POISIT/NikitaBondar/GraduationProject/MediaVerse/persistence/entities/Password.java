package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "password")
    private String password;

}
