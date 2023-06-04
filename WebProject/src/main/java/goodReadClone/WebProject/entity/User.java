package goodReadClone.WebProject.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_aplication")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@ToString
    public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Shelf> shelfs = new HashSet<>();

    public User(String name, String lastname, String username, String email, String password, LocalDate dateBirth, String image, String description) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.image = image;
        this.description = description;
        this.shelfs.add(new Shelf("Read",true));
        this.shelfs.add(new Shelf("Currently Reading",true));
        this.shelfs.add(new Shelf("Want to Read",true));
    }

    public User() {
        this.shelfs.add(new Shelf("Read",true));
        this.shelfs.add(new Shelf("Currently Reading",true));
        this.shelfs.add(new Shelf("Want to Read",true));
    }

    public Shelf getShelfById(Long id) {
        for (Shelf shelf: shelfs) {
            if(shelf.getId().equals(id)){
                return shelf;
            }
        }
        return null;
    }

    public Shelf removeShelf(Shelf shelf){
        shelfs.remove(shelf);
        Set<Shelf> newS = new HashSet<>();
        for(Shelf s: shelfs){
            if(!s.getId().equals(shelf.getId())){
                newS.add(s);
            }
        }
        shelfs = newS;
        return shelf;
    }
    public void addShelf(Shelf createdShelf) {
        shelfs.add(createdShelf);
    }
}
