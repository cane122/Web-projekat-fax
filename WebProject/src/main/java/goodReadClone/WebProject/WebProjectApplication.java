package goodReadClone.WebProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import goodReadClone.WebProject.repository.BookRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("goodReadClone.WebProject.entity")
public class WebProjectApplication {
	//@Autowired
	//private BookRepository BookRepository;


	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}
}
