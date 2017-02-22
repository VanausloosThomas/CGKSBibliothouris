package be.cegeka.bibliothouris.domain.exceptions;

/**
 * Created by stijnli on 25/01/2017.
 */
public class NoSuchAuthorException extends Exception {
    public NoSuchAuthorException(String message){
        super(message);
    }
}
