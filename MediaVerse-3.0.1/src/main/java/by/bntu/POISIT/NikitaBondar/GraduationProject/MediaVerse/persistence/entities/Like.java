package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;


import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.ToStringBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "likes")
public class Like {
    @EmbeddedId
    private LikesId id;

    public Like(){
        this.id = new LikesId();
    }

    public Like(UUID postId, UUID userId){
        this.id = new LikesId(postId, userId);
    }

    public LikesId getId() {
        return id;
    }


    public void setId(LikesId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("PostId", id.postId)
                .append("UserId", id.userId)
                .toString();
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class LikesId implements Serializable {
        @Column(name = "post_id")
        private UUID postId;

        @Column(name = "user_id")
        private UUID userId;
    }
}
