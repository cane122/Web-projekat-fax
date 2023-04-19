package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
