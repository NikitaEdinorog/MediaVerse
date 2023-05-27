package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {
    public static final String pathToUserDir = "mediaverse_front/public/images/storage/%s";
    public static final String pathToUserDirForFront = "images/storage/%s";
    public static final String pathToPosts = "/posts/%s";

    public static String getUserAvatarPath(String user, String avatarFileName){
        return pathToUserDir.formatted(user) + '/' + avatarFileName;
    }

    public static  String getUserPostsPath(String user, String post){
        return pathToUserDir.formatted(user) + pathToPosts.formatted(post)+'/';
    }

    public static String getUserAvatarPathForFront(String user, String avatarFileName){
        return pathToUserDirForFront.formatted(user) + '/' + avatarFileName;
    }

    public static  String getUserPostsPathForFront(String user, String post){
        return pathToUserDirForFront.formatted(user) + pathToPosts.formatted(post)+'/';
    }


    public List<String> writePostImages(String user, String post, List<MultipartFile> files){

        List<String> fileNames= new ArrayList<>();

        File postDir = new File("test/inTest");

        for (int i = 0; i < files.size(); i++) {
            try {
                File postDirs = new File(getUserPostsPath(user, post));
                postDirs.mkdirs();
                postDir = postDirs;
                int len = files.get(i).getOriginalFilename().split("\\.").length;
                String type = files.get(i).getOriginalFilename().split("\\.")[len-1];
                String fileName = "%s_%s.%s".formatted(post,1 + i, type);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(postDirs+"/"+fileName)));
                byte[] bytes = files.get(i).getBytes();
                stream.write(bytes);
                stream.close();
                fileNames.add(fileName);
                System.out.println("Вы удачно загрузили "+fileName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                postDir.delete();
            }
        }
        return  fileNames;
    }
}
