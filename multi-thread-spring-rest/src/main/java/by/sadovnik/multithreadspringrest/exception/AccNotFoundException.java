package by.sadovnik.multithreadspringrest.exception;

import by.sadovnik.multithreadspringrest.utils.ExceptionMessage;

public class AccNotFoundException extends RuntimeException{

    public AccNotFoundException() {
        super(ExceptionMessage.ACCOUNT_NOT_FOUND.getMessage());
    }
}
