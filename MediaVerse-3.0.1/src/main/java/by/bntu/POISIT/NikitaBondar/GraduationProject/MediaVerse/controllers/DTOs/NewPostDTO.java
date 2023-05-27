package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.controllers.DTOs;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class NewPostDTO {

    private List<MultipartFile> files;
    private String description;
    private String[] tags;

    public NewPostDTO(MultipartFile file1,
                      MultipartFile file2,
                      MultipartFile file3,
                      MultipartFile file4,
                      MultipartFile file5,
                      String description,
                      String[] tags) {

        this.files = new ArrayList<>();
        if (file1 != null) files.add(file1);
        if (file2 != null) files.add(file2);
        if (file3 != null) files.add(file3);
        if (file4 != null) files.add(file4);
        if (file5 != null) files.add(file5);
        this.description = description;
        this.tags = tags;
    }

    public NewPostDTO() {
    }

    public NewPostDTO(List<MultipartFile> files, String description, String[] tags) {
        this.files = files;
        this.description = description;
        this.tags = tags;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this)
                .append("files", files)
                .append("description", description)
                .append("tags", tags)
                .toString();
    }
}
