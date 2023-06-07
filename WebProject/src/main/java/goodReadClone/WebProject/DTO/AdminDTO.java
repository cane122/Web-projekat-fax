package goodReadClone.WebProject.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import goodReadClone.WebProject.entity.Book;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class AdminDTO {
    protected String name;
    protected String lastname;

    protected String username;

    protected String email;
    protected String conformationEmail;

    protected String password;
    protected String conformationPassword;

    protected LocalDate dateBirth;

    protected String image;

    protected String description;
    private boolean active;
    private Set<Long> listBooksId;
}
