package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "user_id")
    private UUID userId;
}
