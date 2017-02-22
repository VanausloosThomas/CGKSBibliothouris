package be.cegeka.bibliothouris.application;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.books.BookService;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchAuthorException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(value ="",method = RequestMethod.GET)
    public

    @ResponseBody
    List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/ISBN/{isbn}" , method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getBook(@PathVariable("isbn") String ISBN){
        try {
            return new ResponseEntity<>(bookService.getBookByISBN(ISBN), HttpStatus.ACCEPTED);
        }catch (NoSuchISBNException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public

    @ResponseBody
    ResponseEntity addBook(@RequestParam(value = "ISBN", required = true) String ISBN,@RequestParam(value= "Title", required = true) String bookTitle,
                 @RequestParam(value= "Firstname", required = false) String authorFirstName,@RequestParam(value= "Lastname", required = true) String authorLastName) {
        try {
            bookService.addBook((ISBN),bookTitle,authorFirstName,authorLastName);
            return new ResponseEntity<>("Book added successfully", HttpStatus.ACCEPTED);
        } catch (NoSuchISBNException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
    public
    @ResponseBody

    ResponseEntity getBookByAuthor (@PathVariable("author") String author)
    {
        try {
            return new ResponseEntity<>(bookService.getBookByAuthor(author),HttpStatus.ACCEPTED);
        } catch (NoSuchAuthorException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public
    @ResponseBody

    ResponseEntity getBooksByTitle (@PathVariable("title") String title)
    {
        try {
            return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.ACCEPTED);
        } catch (NoSuchTitleException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
