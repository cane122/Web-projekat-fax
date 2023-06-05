package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.repository.ReviewRepository;
import goodReadClone.WebProject.repository.ShelfInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ShelfInstanceRepository shelfInstanceRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    public void save(Review reviewToBeAdded) {
        reviewRepository.save(reviewToBeAdded);
    }

    public void deleteReview(Long idShelfInstance) {
        ShelfInstance shelfInstancelocal= shelfInstanceRepository.findById(idShelfInstance).get();
        shelfInstancelocal.setReview(null);
        reviewRepository.deleteById(idShelfInstance);
    }
}
