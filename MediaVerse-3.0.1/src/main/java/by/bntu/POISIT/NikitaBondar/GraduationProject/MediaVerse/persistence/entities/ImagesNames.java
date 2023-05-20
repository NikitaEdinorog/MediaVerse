package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;



import jakarta.persistence.Id;
import lombok.Data;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "images_names")
public class ImagesNames {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "names_id_seq_generator")
    @SequenceGenerator(name = "names_id_seq_generator", sequenceName = "images_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "name")
    private String imageName;

    @Transient
    private String path;
}
