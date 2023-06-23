package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.BookDTO;
import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private UserService userService;

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
    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
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
    public ResponseEntity<Book> changeBook(BookDTO newB, @PathVariable("id") Long id, HttpSession session){
        String userRole = (String) session.getAttribute("user_type");
        if(userRole == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(!userRole.equals("Author") && !userRole.equals("Admin")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Set<Author> authors = new HashSet<>();
        authors.add(authorService.findByUsername(newB.getAuthors()));

        Book book = new Book(id, newB.getTitle(), newB.getImage(), newB.getIsbn(), newB.getDatePublished(),newB.getPages(), newB.getDescription(), genreService.findByName(newB.getGenre()),authors);
        bookService.save(book);

        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    //TODO SKOntati sta je ovo i sto nam ne treba
    @PutMapping("/editBook/{id_book}")
    public ResponseEntity<String> editBookOnShelf(@PathVariable("id_book") Long id_book, @RequestBody BookDTO bookdto, HttpSession session){
        User user = (User) session.getAttribute("user");
        Optional<Book> si = bookService.findById(id_book);
        if(si.isEmpty()){
            return new ResponseEntity<>("No book",HttpStatus.NOT_FOUND);
        }
        si.get().setISBN(bookdto.getIsbn());
        si.get().setDatePublished(bookdto.getDatePublished());
        si.get().setImage(bookdto.getImage());
        si.get().setGenre(genreService.findByName(bookdto.getGenre()));
        si.get().setPages(bookdto.getPages());
        si.get().setTitle(bookdto.getTitle());
        si.get().setDescription(bookdto.getDescription());
        bookService.save(si.get());
        return new ResponseEntity<>("Uspesno dodata knjiga na policu",HttpStatus.OK);
    }

    @PostMapping("/book/addBook/")
    public ResponseEntity<Book> addBookToRepository(@RequestBody BookDTO book, HttpSession session){
        User user = (User) session.getAttribute("user");
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Reader")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Set<Author> authors = new HashSet<>();
        authors.add(authorService.findByUsername(book.getAuthors()));
        Book newBook = new Book(book.getTitle(), book.getImage(), book.getIsbn(), book.getDatePublished(),book.getPages(), book.getDescription(), genreService.findByName(book.getGenre()),authors);
        bookService.save(newBook);
        return new ResponseEntity<>(newBook,HttpStatus.OK);
    }
    @DeleteMapping("/deleteBook/{book_id}")
    public ResponseEntity<Book> removeBook(@PathVariable Long book_id, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || !user_type.equals("Admin")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Book> book = bookService.findById(book_id);
        if(book.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!book.get().getReviews().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.remove(book_id);
        return new ResponseEntity<>(book.get(),HttpStatus.OK);
    }
}
