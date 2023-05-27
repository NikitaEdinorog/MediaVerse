package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.logic.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tag;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Tags_Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class NewTagLists {

    private List<Tags_Posts> tagsPosts;

    private List<Tag> newTags;
    private List<Tags_Posts> tagPostsToNewTags;

    public NewTagLists() {
        this.tagsPosts = new ArrayList<>();
        this.newTags = new ArrayList<>();
        this.tagPostsToNewTags = new ArrayList<>();
    }
}
