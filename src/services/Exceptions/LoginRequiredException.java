package services.Exceptions;

public class LoginRequiredException extends RuntimeException{
    public LoginRequiredException(String message){
        super(message);
    }
}
