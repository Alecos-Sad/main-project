package by.sadovnick.yandexmail;

import by.sadovnick.yandexmail.service.DefaultEmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class YandexMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(YandexMailApplication.class, args);
    }
}
