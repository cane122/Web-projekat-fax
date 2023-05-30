package goodReadClone.WebProject.entity;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Genre genre;
    @ManyToMany
    private Set<Author> listAuthors = new HashSet<Author>();
    @OneToMany
    private Set<Review> review = new HashSet<Review>();

    public Book(String title, String image, String isbn, Date datePublished, int pages, String description, Genre genre, Set<Author> listA, Set<Review> listR){
        this.title = title;
        this.image = image;
        this.ISBN = isbn;
        this.datePublished = datePublished;
        this.pages = pages;
        this.description = description;
        this.genre = genre;
        this.listAuthors = listA;
        this.review = listR;

    }

    public Book(Book book) {
        this(book.title, book.image,book.ISBN, book.datePublished, book.pages
                , book.description,book.genre, book.listAuthors, book.review);
    }

}
