package services.Exceptions;

public class UsernameExistsException extends DiaryAppException{
    public UsernameExistsException(String message){
        super(message);
    }
}
