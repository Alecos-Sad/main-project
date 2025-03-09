package by.sadovnick.datastorekafkaredis.web.service;

public interface CDCEventConsumer {

    void handle(String message);
}