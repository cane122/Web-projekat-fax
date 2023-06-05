package goodReadClone.WebProject.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
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
}
