package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags_posts")
public class Tags_Posts
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_posts_id_seq_generator")
    @SequenceGenerator(name = "tags_posts_id_seq_generator", sequenceName = "tags_post_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "tag_id")
    private Long tagId;

}
