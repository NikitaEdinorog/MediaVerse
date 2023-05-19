package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;



import jakarta.persistence.*;
import lombok.Data;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;


import java.sql.Timestamp;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "comment_text")
    private String commentText;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;
}
