package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class MediaVerseApplication {

	@Bean
	 MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("51200KB"));
		factory.setMaxRequestSize(DataSize.parse("51200KB"));
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(MediaVerseApplication.class, args);
	}

}
