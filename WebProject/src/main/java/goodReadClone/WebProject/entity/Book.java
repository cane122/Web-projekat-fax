package goodReadClone.WebProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String image;
    @Column(unique = true, nullable = false)
    private String ISBN;
    @Column(name = "date_published")
    private Date datePublished;
    @Column(nullable = false)
    private int pages;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Genre genre;
    @ManyToMany
    @JsonManagedReference
    private Set<Author> listAuthors = new HashSet<>();
    @OneToMany
    private Set<Review> reviews = new HashSet<>();

    public Book(Long id, String title, String image, String ISBN, Date datePublished, int pages, String description, Genre genre, Set<Author> listAuthors) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.pages = pages;
        this.description = description;
        this.genre = genre;
        this.listAuthors = listAuthors;
    }
    public Book(){}

    public Book(String title, String image, String ISBN, Date datePublished, int pages, String description, Genre genre, Set<Author> listAuthors) {
        this.title = title;
        this.image = image;
        this.ISBN = ISBN;
        this.datePublished = datePublished;
        this.pages = pages;
        this.description = description;
        this.genre = genre;
        this.listAuthors = listAuthors;
    }
}
