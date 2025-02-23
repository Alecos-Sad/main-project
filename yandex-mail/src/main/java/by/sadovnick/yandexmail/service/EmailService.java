package by.sadovnick.yandexmail.service;

import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface EmailService {

    void sendSimpleEmail(String toAddress, String subject, String message);
    void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException;
}
