package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Review;
<<<<<<< Updated upstream
import goodReadClone.WebProject.entity.ShelfInstance;
=======
>>>>>>> Stashed changes
import goodReadClone.WebProject.repository.ReviewRepository;
import goodReadClone.WebProject.repository.ShelfInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ShelfInstanceRepository shelfInstanceRepository;
=======

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
>>>>>>> Stashed changes

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

<<<<<<< Updated upstream
    public void save(Review reviewToBeAdded) {
        reviewRepository.save(reviewToBeAdded);
    }

    public void deleteReview(Long idShelfInstance) {
        ShelfInstance shelfInstancelocal= shelfInstanceRepository.findById(idShelfInstance).get();
        shelfInstancelocal.setReview(null);
=======
    public void save(Review newReview) {
        reviewRepository.save(newReview);
    }

    public void deleteReview(Long idShelfInstance) {
>>>>>>> Stashed changes
        reviewRepository.deleteById(idShelfInstance);
    }
}
