package goodReadClone.WebProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int grade;

    @Column
    private String text;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private User user;

    @ManyToOne
    @JsonBackReference
    private Book books;

    public Review(String text, int grade, LocalDate reviewDate) {
        this.text = text;
        this.grade = grade;
        this.reviewDate = reviewDate;
    }
    public Review(){}
}
