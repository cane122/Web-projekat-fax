package goodReadClone.WebProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int grade;

    @Column
    private String text;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
