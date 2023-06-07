package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

enum Status {
    WAITING,
    CONFIRMED,
    DENIED}
@Entity
@Getter
@Setter
@ToString
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
    private Status status = Status.WAITING;

    @OneToOne
    private User user;

    public Request(String email, String phone, String message, LocalDate date, String status, User user) {
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.date = date;
        this.status = (Status) Enum.valueOf(Status.class,status);
        this.user = user;
    }

    public String getStatusString() {
        switch (status){
            case WAITING -> {
                return "WAITING";
            }
            case DENIED -> {
                return "DENIED";
            }
            case CONFIRMED -> {
                return "CONFIRMED";
            }
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    public void setStatusString(String accepted) {
        switch (accepted){
            case "WAITING" -> {
                status = Status.WAITING;
                break;
            }
            case "DENIED" -> {
                status = Status.DENIED;
            }
            case "CONFIRMED" -> {
                status = Status.CONFIRMED;
            }
            default -> throw new IllegalStateException("Unexpected value: " + status);
        }
    }
}
