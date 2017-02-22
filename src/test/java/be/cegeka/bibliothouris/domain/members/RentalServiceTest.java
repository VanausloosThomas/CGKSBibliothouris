package be.cegeka.bibliothouris.domain.members;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;
import be.cegeka.bibliothouris.domain.rentals.RentalRepository;
import be.cegeka.bibliothouris.domain.rentals.RentalService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.inject.Inject;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by stijnli on 30/01/2017.
 */
public class RentalServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private MemberService memberService;

    @InjectMocks
    private RentalService rentalService;

    @Test
    public void test_GetBooksByMember_shouldReturnCorrectBooks(){
        Book testBook1 = new Book("9780747960722","Harry Potter and the philosophers' stone","JK","Rowling");
        Book testBook2 = new Book("9789513114725","Harry Potter and the chamber of secrets","JK","Rowling");
        Book testBook3 = new Book("9788700459946","Harry Potter and the prisoner of azkaban","JK","Rowling");
        Member testMember = new Member("7","Liekens","Stijn","Aarschotsesteenweg",370,"3012","wilsele");
        when(rentalRepository.getBooksByMember(testMember)).thenReturn(Arrays.asList(testBook1,testBook2,testBook3));
        try {
            when(memberService.getMember("7")).thenReturn(testMember);
            assertThat(rentalService.getBooksByMember("7")).containsExactly(testBook1,testBook2,testBook3);
        } catch (INSSAlreadyExistsException e) {
            e.printStackTrace();
        }
    }



}
