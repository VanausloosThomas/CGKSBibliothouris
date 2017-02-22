package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.books.BookRepository;
import be.cegeka.bibliothouris.domain.books.BookService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.security.PrivateKey;

/**
 * Created by dieterp on 26/01/2017.
 */
public class BooksServiceTest {

    // test met mocks
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private BookService bookService;

    // @Mock
    // private BookValidator bookValidator;

    @Mock
    private BookRepository bookRepository;
}
