package be.cegeka.bibliothouris.domain.exceptions;

/**
 * Created by stijnli on 25/01/2017.
 */
public class NoSuchTitleException extends Exception {
    public NoSuchTitleException(String message){
        super(message);
    }
}
