package be.cegeka.bibliothouris.domain.rentals;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.books.BookRepository;
import be.cegeka.bibliothouris.domain.books.BookService;
import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.members.Member;
import be.cegeka.bibliothouris.domain.members.MemberRepository;
import be.cegeka.bibliothouris.domain.members.MemberService;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by stijnli on 26/01/2017.
 */
@Named
public class RentalService {

    @Inject
    private RentalRepository rentalRepository;
    @Inject
    private BookService bookService;
    @Inject
    private MemberService memberService;

    public void addRental(String INSS, String ISBN) throws NoSuchISBNException,INSSAlreadyExistsException {
        Book book = bookService.getFirstBookByISBN(ISBN);
        Member member = memberService.getMember(INSS);
        rentalRepository.addRental(new Rental(member,book));
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.getAllRentals();
    }

    public boolean removeRental(String ISBN) throws NoSuchISBNException {
        Rental rental=rentalRepository.getRentalByISBN(ISBN);
        boolean returnedToLate=checkDueDate(rental);
        rentalRepository.removeRental(rental);
        return returnedToLate;
    }

    private boolean checkDueDate(Rental rental){
        boolean checkDueDate=false;
        if(rental.getDueDate().isBefore(LocalDate.now())){
            checkDueDate=true;
        }
        return checkDueDate;
    }

    public List<Book> getBooksByMember(String INSS) throws INSSAlreadyExistsException{

        Member member = memberService.getMember(INSS);
        return rentalRepository.getBooksByMember(member);

    }
}

