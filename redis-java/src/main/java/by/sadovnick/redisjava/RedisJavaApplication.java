package by.sadovnick.redisjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisJavaApplication.class, args);
	}

}
