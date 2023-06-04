package goodReadClone.WebProject.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {
    private int grade;
    private String text;
    private LocalDate reviewDate;
}
