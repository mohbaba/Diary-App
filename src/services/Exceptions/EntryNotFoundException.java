package services.Exceptions;

public class EntryNotFoundException extends DiaryAppException{
    public EntryNotFoundException(String message){
        super(message);
    }
}
