package be.cegeka.bibliothouris.domain.books;

import be.cegeka.bibliothouris.domain.exceptions.NoSuchAuthorException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchTitleException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
public class BookRepository {

    private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks()
    {
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooksByISBN(String ISBN) throws NoSuchISBNException {
        List<Book> foundBooks = new ArrayList<>();
        String testISBN=ISBN.replaceAll("\\*",".+");
        if(testISBN.length()>1) {
            if (testISBN.substring(0, 2).equals(".+")) {
                testISBN = ".*" + testISBN.substring(2, testISBN.length());
            }
            if (testISBN.substring(testISBN.length() - 2, testISBN.length()).equals(".+")) {
                testISBN = testISBN.substring(0, testISBN.length() - 2) + ".*";
            }
        }
        for (Book book : books ) {
            if(book.getISBN().matches(testISBN)){
                foundBooks.add(book);
            }
        }
        if(foundBooks.isEmpty()){
            throw new NoSuchISBNException("No book found with this ISBN : " + ISBN );
        }
        return foundBooks;
    }

    public List<Book> getBooksByAuthor(String name) throws NoSuchAuthorException {
        List<Book> foundBooks = new ArrayList<>();
        String testName=name.replaceAll("\\*",".+");
        if(testName.length()>1) {
            if (testName.substring(0, 2).equals(".+")) {
                testName = ".*" + testName.substring(2, testName.length());
            }
            if (testName.substring(testName.length() - 2, testName.length()).equals(".+")) {
                testName = testName.substring(0, testName.length() - 2) + ".*";
            }
        }
        for (Book book: books)
        {
            if ((String.valueOf(book.getauthorFirstName()).matches(testName)) || String.valueOf(book.getauthorLastName()).matches(testName))
            {
                foundBooks.add(book);
            }
        }
        if(foundBooks.isEmpty()){
            throw new NoSuchAuthorException("No book found with this author : " + name);
        }
        return foundBooks;
    }

    public List<Book> getBookByTitle(String title) throws NoSuchTitleException {
        List<Book> foundBooks = new ArrayList<>();
        String testTitle=title.replaceAll("\\*",".+");
        if(testTitle.length()>1) {
            if (testTitle.substring(0, 2).equals(".+")) {
                testTitle = ".*" + testTitle.substring(2, testTitle.length());
            }
            if (testTitle.substring(testTitle.length() - 2, testTitle.length()).equals(".+")) {
                testTitle = testTitle.substring(0, testTitle.length() - 2) + ".*";
            }
        }
        for (Book book : books)
        {
            if ((String.valueOf(book.getBookTitle()).matches(testTitle)))
            {
                foundBooks.add(book);
            }
        }
        if(foundBooks.isEmpty()){
            throw new NoSuchTitleException("No book found with this title: " + title);
        }
        return foundBooks;
    }

    public Book getFirstBookByISBN(String ISBN) throws NoSuchISBNException {
        for (Book book : books ) {
            if(book.getISBN().matches(ISBN)){
                return book;
            }
        }
        throw new NoSuchISBNException("No book found with this ISBN : " + ISBN );
    }

}
