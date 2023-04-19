package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

enum Role {
    READER,
    AUTHOR,
    ADMIN
}

@Entity
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private LocalDate dateB;

    @Column
    private String image;

    @Column
    private String description;

    @Column
    private Role role;

    @Column
    private boolean active;

    @Column
    private Role list;



}
