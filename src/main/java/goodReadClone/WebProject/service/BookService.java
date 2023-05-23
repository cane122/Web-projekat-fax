package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Book;
import goodReadClone.WebProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findByTitle(String title){
        Optional<Book> found = bookRepository.findByTitle(title);
        return found.orElse(null);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

//    public List<Book> search(String param){
//        List<Book> temp = new ArrayList<>();
//        for(int i = 0; i < bookRepository.findAll().size(); i++){
//
//        }
//    }

    public BookRepository getRepo(){
        return bookRepository;
    }
}
