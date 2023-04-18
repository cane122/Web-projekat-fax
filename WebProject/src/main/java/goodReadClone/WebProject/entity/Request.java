package goodReadClone.WebProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
enum Status {
    WAITING,
    CONFIRMED,
    DENIED}
@Entity
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String message;

    @Column
    private LocalDate date;

    @Column
    private Status status;


}
