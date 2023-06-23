package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.ReviewDTO;
import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.BookService;
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
@CrossOrigin
public class ReviewController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ShelfInstanceService shelfInstanceService;
    @Autowired
    private BookService bookService;

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
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
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
    @DeleteMapping("/user/delete/review/{idReview}/shelf/{id_shelfInstance}")
    public ResponseEntity editReview(@PathVariable Long idReview, @PathVariable Long id_shelfInstance, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        if(shelfInstanceService.findById(id_shelfInstance).isEmpty()){
            return new ResponseEntity<>("Shelf instance not found", HttpStatus.BAD_REQUEST);
        }
        reviewService.deleteReview(id_shelfInstance,idReview);
        return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
    }
}
