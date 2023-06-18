package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.BookDTO;
import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Book changeBook(BookDTO newB, @PathVariable("id") Long id, HttpSession session){
        String userRole = (String) session.getAttribute("user_type");
        if(userRole == null){
            return null;
        }
        if(!userRole.equals("Author") && !userRole.equals("Admin")){
            return null;
        }
        Set<Author> authors = new HashSet<>();
        for(String auth: newB.getAuthors()){
            authors.add(authorService.findByUsername(auth));
        }
        Book book = new Book(id, newB.getTitle(), newB.getImage(), newB.getIsbn(), newB.getDatePublished(),newB.getPages(), newB.getDescription(), genreService.findByName(newB.getGenre()),authors);
        bookService.save(book);

        return book;
    }

    //TODO SKOntati sta je ovo i sto nam ne treba
    @PutMapping("shelf/{id_shelf}/editBook/{id_book}")
    public ResponseEntity<String> editBookOnShelf(@PathVariable("id_shelf") Long id_shelf, @PathVariable("id_book") Long id_book, @RequestBody BookDTO bookdto, HttpSession session){
        User user = (User) session.getAttribute("user");
        Shelf shelf = user.getShelfById(id_shelf);
        if(shelf == null){
            return new ResponseEntity<>("Ne postoji polica ili knjiga", HttpStatus.BAD_REQUEST);
        }
        ShelfInstance si = shelf.getShelfInstanceByBookId(id_book);
        si.getBook().setISBN(bookdto.getIsbn());
        si.getBook().setDatePublished(bookdto.getDatePublished());
        si.getBook().setImage(bookdto.getImage());
        si.getBook().setGenre(genreService.findByName(bookdto.getGenre()));
        si.getBook().setPages(bookdto.getPages());
        si.getBook().setTitle(bookdto.getTitle());
        si.getBook().setDescription(bookdto.getDescription());
        //si.getBook().setListAuthors(bookdto.getAuthors());
        shelf.editShelfInstance(si);
        shelfService.save(shelf);
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
        for(String aut: book.getAuthors()){
            authors.add(authorService.findByUsername(aut));
        }
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
