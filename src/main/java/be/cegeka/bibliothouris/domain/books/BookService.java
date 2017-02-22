package be.cegeka.bibliothouris.domain.books;

import be.cegeka.bibliothouris.domain.exceptions.NoSuchAuthorException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchTitleException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookService {

    @Inject
    private BookRepository bookRepository;
    @Inject
    private BookValidator bookValidator;

    public void addBook(String ISBN, String bookTitle, String authorFirstName, String authorLastName) throws NoSuchISBNException {
        if(bookValidator.isValid(ISBN)){
            bookRepository.addBook(new Book(ISBN,bookTitle,authorFirstName,authorLastName));
        } else
        {
            throw new NoSuchISBNException("ISBN is not valid");
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getBookByISBN(String ISBN) throws NoSuchISBNException{
        return bookRepository.getBooksByISBN(ISBN);
    }

    public List<Book> getBookByAuthor(String name) throws NoSuchAuthorException {
        return bookRepository.getBooksByAuthor(name);
    }

    public List<Book> getBookByTitle(String title) throws NoSuchTitleException {
        return bookRepository.getBookByTitle(title);

    }

    public Book getFirstBookByISBN(String ISBN )throws NoSuchISBNException{
        return bookRepository.getFirstBookByISBN(ISBN);
    }
}
