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
    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "image_1")
    private String image1Name;

    @Column(name = "image_2")
    private String image2Name;

    @Column(name = "image_3")
    private String image3Name;

    @Column(name = "image_4")
    private String image4Name;

    @Column(name = "image_5")
    private String image5Name;

}
