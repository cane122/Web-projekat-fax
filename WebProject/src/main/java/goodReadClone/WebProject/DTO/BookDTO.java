package goodReadClone.WebProject.DTO;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class BookDTO {

    private String title;

    private String image;

    private String ISBN;

    private Date datePublished;

    private int pages;

    private String description;

    private String genre;

    private Set<String> authors;
}
