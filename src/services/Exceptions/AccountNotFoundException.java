package services.Exceptions;

public class AccountNotFoundException extends DiaryAppException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
