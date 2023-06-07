package goodReadClone.WebProject.service;

import goodReadClone.WebProject.DTO.RequestDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    public Set<Author> findAllByUsername(String auth) {
        return authorRepository.findAllByUsernameContainsIgnoreCase(auth);
    }

    public Author findByUsername(String auth) {
        return authorRepository.findByUsername(auth);
    }

    public Optional<Author> findById(Long adminId) {
        return authorRepository.findById(adminId);
    }
}
