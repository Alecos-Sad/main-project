package by.sadovnik.multithreadspringrest.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {

    BAD_BALANCE("Not enough balance for transfer"),
    ACCOUNT_NOT_FOUND("Account not found");

    private final String message;
}
