package be.cegeka.bibliothouris.domain.exceptions;

/**
 * Created by stijnli on 25/01/2017.
 */
public class NoSuchISBNException extends Exception{
    public NoSuchISBNException(String message){
        super(message);
    }

}
