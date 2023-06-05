package goodReadClone.WebProject.DTO;

import goodReadClone.WebProject.entity.Status;
import goodReadClone.WebProject.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

enum Status {
    WAITING,
    CONFIRMED,
    DENIED}

@Data
public class RequestDTO {

        private String email;
        private String phone;
        private String message;
        private LocalDate date;
        private Status status = Status.WAITING;
        private User user;


    }
