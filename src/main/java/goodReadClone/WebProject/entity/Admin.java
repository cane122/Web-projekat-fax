package goodReadClone.WebProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "Admin_User")
public class Admin extends User {

}
