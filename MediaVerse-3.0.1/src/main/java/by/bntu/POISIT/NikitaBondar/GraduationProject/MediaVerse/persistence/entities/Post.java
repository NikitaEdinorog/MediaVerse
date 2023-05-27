package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "author_id")
    private UUID authorId;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Transient
    private List<Tag> tags;

    @Transient
    private List<ImagesNames> images;

    @Transient
    private Long likes;

    @Transient
    private Long comments;

    @Transient
    private String authorUsername;

    public Post(String description, UUID id) {
        this.description = description;
        this.authorId = id;
    }

    public Post(Post post) {
         this.id = post.getId();

        this.description = post.getDescription();

        this.authorId = post.getAuthorId();

        this.timestamp = post.getTimestamp();

        this.tags = new ArrayList<>(post.getTags());

        this.images = new ArrayList<>(post.getImages());

        this.likes = post.getLikes();

        this.comments = post.getComments();
    }

    public void fill(Post data) {
        this.tags = data.getTags();

        this.images = data.getImages();

        this.likes = data.getLikes();

        this.comments = data.getComments();
    }
}
