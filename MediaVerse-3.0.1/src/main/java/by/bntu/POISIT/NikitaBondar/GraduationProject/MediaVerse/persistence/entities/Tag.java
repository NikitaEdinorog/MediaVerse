package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;


import lombok.Data;
import lombok.RequiredArgsConstructor;


import jakarta.persistence.*;


@Entity
@Data
@RequiredArgsConstructor
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_id_seq_generator")
    @SequenceGenerator(name = "tags_id_seq_generator", sequenceName = "tags_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
