package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/api/review")
    public List<Review> getReview(){
        return reviewService.findAll();
    }
    @GetMapping("/api/review/{id}")
    public Review getReview(@PathVariable Long id){
        Optional<Review> review = reviewService.findById(id);
        return review.orElse(null);

    }
}
