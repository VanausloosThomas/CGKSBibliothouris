package be.cegeka.bibliothouris.domain.rentals;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.members.Member;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stijnli on 26/01/2017.
 */

@Named
public class RentalRepository {
    private List<Rental> rentals = new ArrayList<>();

    public List<Rental> getAllRentals(){
        return rentals;
    }

    public void addRental(Rental rental){
        rentals.add(rental);
    }

    public void removeRental(Rental rental) {rentals.remove(rental);}

    public Rental getRentalByISBN(String ISBN) throws NoSuchISBNException {
        for (Rental rental:rentals){
            if(rental.getBook().getISBN().equals(ISBN)){
                return rental;
            }
        }
        throw new NoSuchISBNException("No rental found for book with this ISBN : " + ISBN );
    }

    public List<Book> getBooksByMember(Member member){
        List<Book> result = new ArrayList<>();
        for (Rental rental : rentals) {
            if(rental.getMember().equals(member)){
                result.add(rental.getBook());
            }
        }
        return   result;
    }

}
