package by.sadovnick.datageneratekafka.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class BeanConfig {

    @SneakyThrows
    @Bean
    public XML producerXML() {
        return new XMLDocument(
                new File("D:\\MainProject\\main-project\\data-generate-kafka\\src\\main\\resources\\producer.xml")
        );
    }
}
