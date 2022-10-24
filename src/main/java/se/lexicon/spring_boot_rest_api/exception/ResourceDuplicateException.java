package se.lexicon.spring_boot_rest_api.exception;

public class ResourceDuplicateException extends RuntimeException{

    public ResourceDuplicateException(String message){
        super(message);
    }
}
