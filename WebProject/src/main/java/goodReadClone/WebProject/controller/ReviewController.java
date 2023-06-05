package goodReadClone.WebProject.controller;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import goodReadClone.WebProject.DTO.ReviewDTO;
import goodReadClone.WebProject.entity.Reader;
import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.ReviewService;
import goodReadClone.WebProject.service.ShelfInstanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ShelfInstanceService shelfInstanceService;

    @GetMapping("/api/review")
    public List<Review> getReview(){
        return reviewService.findAll();
    }
    @GetMapping("/api/review/{id}")
    public Review getReview(@PathVariable Long id){
        Optional<Review> review = reviewService.findById(id);
        return review.orElse(null);

    }
    @PutMapping("/user/edit/review/{id_shelfInstance}")
    public ResponseEntity editReview(@PathVariable Long id_shelfInstance, @RequestBody ReviewDTO reviewDTO, HttpSession session){
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        if(shelfInstanceService.findById(id_shelfInstance).isEmpty()){
            return new ResponseEntity<>("Shelf instance not found", HttpStatus.BAD_REQUEST);
        }
        ShelfInstance shInstance = shelfInstanceService.findById(id_shelfInstance).get();
        // create user object
        Review newReview = new Review();
        newReview.setId(shInstance.getReview().getId());
        newReview.setGrade(reviewDTO.getGrade());
        newReview.setText(reviewDTO.getText());
        newReview.setReviewDate(reviewDTO.getReviewDate());

        reviewService.save(newReview);
        return new ResponseEntity<>("Review successfully updated", HttpStatus.OK);
    }
    @DeleteMapping("/user/delete/review/{id_shelfInstance}")
    public ResponseEntity editReview(@PathVariable Long id_shelfInstance, HttpSession session){
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        if(shelfInstanceService.findById(id_shelfInstance).isEmpty()){
            return new ResponseEntity<>("Shelf instance not found", HttpStatus.BAD_REQUEST);
        }
        reviewService.deleteReview(id_shelfInstance);
        return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
    }
}
