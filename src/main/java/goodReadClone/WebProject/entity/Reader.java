package goodReadClone.WebProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reader")
public class Reader extends User {
}
