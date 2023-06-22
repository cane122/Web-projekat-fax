package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.entity.ShelfInstance;
import goodReadClone.WebProject.repository.BookRepository;
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
    @Autowired
    private BookRepository bookRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }
    public void save(Review reviewToBeAdded) {
        reviewRepository.save(reviewToBeAdded);
    }

    public void deleteReview(Long idShelfInstance, Long idReview) {
        ShelfInstance shelfInstancelocal = shelfInstanceRepository.findById(idShelfInstance).get();
        shelfInstancelocal.setReview(null);
        shelfInstancelocal.getBook().deleteReview(idReview);
        bookRepository.save(shelfInstancelocal.getBook());
        shelfInstanceRepository.save(shelfInstancelocal);
        reviewRepository.deleteById(idShelfInstance);

    }
}
