package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.BookDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.Book;
import goodReadClone.WebProject.entity.Review;
import goodReadClone.WebProject.service.AuthorService;
import goodReadClone.WebProject.service.BookService;
import goodReadClone.WebProject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @GetMapping("/api/books")
    public List<Book> getBooks(){
        return bookService.findAll();
    }
    @GetMapping("/api/book/{id}/reviews")
    public Set<Review> getBookReviews(@PathVariable Long id){
        Optional<Book> bookList = bookService.findById(id);
        return bookList.map(Book::getReviews).orElse(null);
    }

//    @GetMapping("/api/searchBook")
//    public List<Book> searchBook(String param){
//        List<Book> searched = bookService.search(param);
//        return searched;
//    }

    @GetMapping("/api/findbookbytitle/{title}")
    public List<Book> getBooksByTitle(@PathVariable("title") String title) {
        return bookService.getRepo().findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/api/findbookbyauthor/{author}")
    public Set<Book> getBooksByAuthor(@PathVariable("author") String author) {
        Set<Author> authorList = authorService.findAllByUsername(author);
        Set<Book> books = new HashSet<>();
        for(Author a: authorList){
            books.addAll(a.getListBooks());
        }
        return books;
    }

    @PutMapping("/api/changeBook/{id}")
    public Book changeBook(BookDTO newB, @PathVariable("id") Long id){
        Set<Author> authors = new HashSet<>();
        for(String auth: newB.getAuthors()){
            authors.add(authorService.findByUsername(auth));
        }
        Book book = new Book(id, newB.getTitle(), newB.getImage(), newB.getISBN(), newB.getDatePublished(),newB.getPages(), newB.getDescription(), genreService.findByName(newB.getGenre()),authors);
        bookService.save(book);

        return book;
    }
}
