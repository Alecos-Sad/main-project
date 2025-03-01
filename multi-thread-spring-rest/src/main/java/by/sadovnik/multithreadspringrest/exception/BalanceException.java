package by.sadovnik.multithreadspringrest.exception;

import by.sadovnik.multithreadspringrest.utils.ExceptionMessage;

public class BalanceException extends RuntimeException{

    public BalanceException() {
        super(ExceptionMessage.BAD_BALANCE.getMessage());
    }
}
