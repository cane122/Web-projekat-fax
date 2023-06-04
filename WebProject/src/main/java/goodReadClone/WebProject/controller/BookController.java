package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.BookDTO;
import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.service.AuthorService;
import goodReadClone.WebProject.service.BookService;
import goodReadClone.WebProject.service.GenreService;
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

    @PutMapping("shelf/{id_shelf}/editBook/{id_book}")
    public ResponseEntity<String> editBookOnShelf(@PathVariable("id_shelf") Long id_shelf, @PathVariable("id_book") Long id_book, HttpSession session){
        User user = (User) session.getAttribute("user");
        Shelf shelf = user.getShelfById(id_shelf);
        Optional<Book> book = bookService.findById(id_book);
        if(shelf == null || book.isEmpty()){
            return new ResponseEntity<>("Ne postoji polica ili knjiga", HttpStatus.BAD_REQUEST);
        }
        int bookOnPrimary = 0;
        for(Shelf s: user.getShelfs()){
            for(ShelfInstance si: s.getShelfInstance()){
                Book knjiga =si.getBook();
                if(knjiga.getId() == id_book){
                    if(s.isPrimary()){
                        bookOnPrimary += 1;
                    }

                }
            }
        }
        if(bookOnPrimary == 1 && shelf.isPrimary() == true){
            return new ResponseEntity<>("Moze max na jedan primarni da se stavi",HttpStatus.BAD_REQUEST);
        }
        if(bookOnPrimary == 0 && shelf.isPrimary() != true){
            return new ResponseEntity<>("Ne moze jer nema je na primarnom",HttpStatus.BAD_REQUEST);
        }
        ShelfInstance si = new ShelfInstance(book.get());
        shelf.getShelfInstance().add(si);
        Shelf newShelf = shelfService.save(shelf);
        si = shelfInstanceService.save(si);
        return new ResponseEntity<>("Uspesno dodata knjiga na policu",HttpStatus.OK);
    }
}
