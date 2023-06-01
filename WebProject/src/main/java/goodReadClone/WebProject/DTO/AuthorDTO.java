package goodReadClone.WebProject.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDTO {
    @Column
    protected String name;

    @Column
    protected String lastname;

    @Column
    protected String username;

    @Column(unique = true)
    protected String email;

    @Column
    protected String password;

    @Column(name = "date_birth")
    protected LocalDate dateBirth;

    @Column
    protected String image;

    @Column
    protected String description;

    @Column
    protected boolean active;
}
