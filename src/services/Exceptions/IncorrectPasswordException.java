package services.Exceptions;

public class IncorrectPasswordException extends DiaryAppException{
    public IncorrectPasswordException(String message){
        super(message);
    }
}
