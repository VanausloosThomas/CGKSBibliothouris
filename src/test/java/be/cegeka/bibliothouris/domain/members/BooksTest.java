package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.books.BookValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by dieterp on 26/01/2017.
 */
public class BooksTest {
    // maak boek aan
    Book boek = new Book("586688865", "De weg naar de testing", "Sanne", "Vermeiren");


    // test ISBN
    @Test
    public void getISBN() throws Exception
    {
        Assertions.assertThat(boek.getISBN()).isEqualTo("586688865");
    }

    // test title
    @Test
    public void getTitle() throws Exception
    {
        Assertions.assertThat(boek.getBookTitle()).isEqualTo("De weg naar de testing");
    }

    // test author firstname
    @Test
    public void getFirstName() throws Exception
    {
        Assertions.assertThat(boek.getauthorFirstName()).isEqualTo("Sanne");
    }

    // Test author last name
    @Test
    public void getLastName() throws Exception
    {
        Assertions.assertThat(boek.getauthorLastName()).isEqualTo("Vermeiren");
    }

    // Test details output
    @Test
    public void getdetails() throws Exception
    {
        Assertions.assertThat(boek.getDetails()).isEqualTo("586688865" + System.lineSeparator() + "De weg naar de testing" + System.lineSeparator() + "Sanne" + System.lineSeparator() +  "Vermeiren");
    }

    // Test ISBN validator
    @Test
    public void ISBNValidator() throws Exception
    {
        BookValidator bv = new BookValidator();
        Assertions.assertThat(bv.isValid("9789027439642")).isEqualTo(true);
    }

    // Search Book





}
