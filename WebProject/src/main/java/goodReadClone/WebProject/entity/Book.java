package goodReadClone.WebProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String image;
    @Column
    private String ISBN;
    @Column(name = "date_published")
    private LocalDate datePublished;
    @Column
    private int pages;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Genre genre;
    @Column
    private String review;


}
