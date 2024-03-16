package services.Exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String message){
        super(message);
    }
}
