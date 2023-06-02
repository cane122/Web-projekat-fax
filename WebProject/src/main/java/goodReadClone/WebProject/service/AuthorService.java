package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
