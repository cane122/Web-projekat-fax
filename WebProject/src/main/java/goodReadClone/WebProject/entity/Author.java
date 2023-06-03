package goodReadClone.WebProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author extends User {
    @Column
    private boolean active;

    @ManyToMany(mappedBy = "listAuthors")
    private Set<Book> listBooks = new HashSet<>();

    public Author(String name, String lastname, String username, String email, String password, LocalDate dateBirth, String image, String description) {
        super(name, lastname, username, email, password, dateBirth, image, description);
        this.active = false;
    }
    public Author(){}
}
