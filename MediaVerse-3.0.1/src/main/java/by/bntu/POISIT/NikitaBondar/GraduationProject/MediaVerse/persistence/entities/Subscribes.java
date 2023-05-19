package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "subscribes")
public class Subscribes {

    @EmbeddedId
    private CompositeId compositeId;

    public Subscribes(){}

    public Subscribes(UUID authorId, UUID subscriber) {
        this.compositeId = new CompositeId(authorId,subscriber);
    }


    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class CompositeId implements Serializable {

        @Column(name = "author_id")
        private UUID authorId;

        @Column(name = "subscriber_id")
        private UUID subscriber;
    }
}
