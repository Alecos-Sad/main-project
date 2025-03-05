package by.sadovnick.socksapihhru;

import by.sadovnick.socksapihhru.dto.SockDto;
import by.sadovnick.socksapihhru.mapper.SockMapper;
import by.sadovnick.socksapihhru.model.Sock;
import by.sadovnick.socksapihhru.repository.SockRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
class SocksApiHhRuApplicationTests {

    @Resource
    private SockMapper sockMapper;

    @MockitoBean
    private SockRepository sockRepository;

    @Test
    void testMapping(){
        Sock sock = Sock.builder()
                .id(1L)
                .color("black")
                .cottonPercentage(20)
                .quantity(100)
                .build();

        SockDto sockDto = SockDto.builder()
                .color("black")
                .cottonPercentage(20)
                .quantity(100)
                .build();

        Sock sockToDto = sockMapper.dtoToEntity(sockDto);
        SockDto sockDtoToEntity = sockMapper.entityToDto(sock);
        sock.setId(null);
        assertEquals(sockToDto, sock);
        assertEquals(sockDtoToEntity, sockDto);
    }
}
