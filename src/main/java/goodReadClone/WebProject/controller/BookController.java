package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Book;
import goodReadClone.WebProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks(){
        List<Book> bookList = bookService.findAll();
        return bookList;
    }

//    @GetMapping("/api/searchBook")
//    public List<Book> searchBook(String param){
//        List<Book> searched = bookService.search(param);
//        return searched;
//    }

    @GetMapping("/api/{title}")
    public List<Book> getBooksByTitle(@PathVariable("title") String title) {
        return bookService.getRepo().findByTitleContainingIgnoreCase(title);
    }

}
