package be.cegeka.bibliothouris.application;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.exceptions.INSSAlreadyExistsException;
import be.cegeka.bibliothouris.domain.exceptions.NoSuchISBNException;
import be.cegeka.bibliothouris.domain.rentals.Rental;
import be.cegeka.bibliothouris.domain.rentals.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by stijnli on 26/01/2017.
 */

@Controller
@RequestMapping("/rental")
public class RentalController {

    @Inject
    private RentalService rentalService;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<Rental> getRentals(){
        return rentalService.getAllRentals();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addRental(@RequestParam(value = "INSS",required = true) String INSS,
                                    @RequestParam(value = "ISBN",required = true) String ISBN){
        try {
            rentalService.addRental(INSS,ISBN);
            return new ResponseEntity<>("Rental added succesfully",HttpStatus.ACCEPTED);
        }catch (INSSAlreadyExistsException | NoSuchISBNException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/remove/ISBN/{isbn}" , method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity removeRental(@PathVariable(value = "isbn",required = true) String ISBN){
        try {
            boolean returnedToLate=rentalService.removeRental(ISBN);
            if(returnedToLate) {
                return new ResponseEntity<>("Rental removed succesfully but was returned to late", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Rental removed succesfully", HttpStatus.ACCEPTED);
        }catch (NoSuchISBNException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/members/{issn}",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getBooksByMember(@PathVariable(value = "issn", required = true) String  ISSN){
        try {

            return new ResponseEntity<>(rentalService.getBooksByMember(ISSN), HttpStatus.ACCEPTED);
        } catch (INSSAlreadyExistsException e) {

            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);

        }
    }
}
