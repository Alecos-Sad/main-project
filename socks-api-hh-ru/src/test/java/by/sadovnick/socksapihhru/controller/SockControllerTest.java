package by.sadovnick.socksapihhru.controller;

import by.sadovnick.socksapihhru.dto.SockDto;
import by.sadovnick.socksapihhru.mapper.SockMapperImpl;
import by.sadovnick.socksapihhru.model.Sock;
import by.sadovnick.socksapihhru.repository.SockRepository;
import by.sadovnick.socksapihhru.service.impl.SockServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStream;
import java.util.Optional;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@Import(value = {
        SockServiceImpl.class,
        SockMapperImpl.class
})
class SockControllerTest {

    @Resource
    MockMvc mockMvc;

    @Resource
    ObjectMapper objectMapper;

    @MockitoBean
    private SockRepository sockRepository;

    private Sock sock;
    private SockDto sockDto;

    @BeforeEach
    void setUp() {
        sock = Sock.builder()
                .id(1L)
                .color("red")
                .cottonPercentage(10)
                .quantity(2)
                .build();

        sockDto = SockDto.builder()
                .color("red")
                .cottonPercentage(10)
                .quantity(2)
                .build();
    }

    @ParameterizedTest
    @CsvSource({
            "true",
            "false"
    })
    void incomeTest(boolean isSockPresentInDB) throws Exception {
        String url = "/api/socks/income";
        if (isSockPresentInDB) {
            when(sockRepository.findByColorAndCottonPercentage(sockDto.getColor(), sockDto.getCottonPercentage()))
                    .thenReturn(Optional.of(sock));
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(sockDto))
                    .characterEncoding("UTF-8")
            ).andReturn();
            Assertions.assertEquals(
                    "Colors: red and cotton content 10, found in the database, added quantity: 2, final amount: 4",
                    mvcResult.getResponse().getContentAsString());
        } else {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(sockDto))
                    .characterEncoding("UTF-8")
            ).andReturn();
            Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
            Assertions.assertEquals(
                    "Colors: red and cotton content 10, not found in the database, added default parameters",
                    mvcResult.getResponse().getContentAsString());
        }
    }

    @Test
    void outcomeTest() throws Exception {
        String url = "/api/socks/outcome";
        when(sockRepository.findByColorAndCottonPercentage(sockDto.getColor(), sockDto.getCottonPercentage()))
                .thenReturn(Optional.of(sock));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sockDto))
                .characterEncoding("UTF-8")
        ).andReturn();
        Assertions.assertEquals(
                "Colors: red and cotton content 10, found in the database, removed quantity: 2, final amount: 0",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateSockTest() throws Exception {
        String url = "/api/socks/%d";
        when(sockRepository.findById(anyLong()))
                .thenReturn(Optional.of(sock));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(url.formatted(1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sockDto))
                        .characterEncoding("UTF-8")

                ).andExpect(status().is(200))
                .andReturn();

        Assertions.assertEquals(
                "Colors: red and cotton content 10, found in the database by id: 1, and updates successfully by new value color: red and cotton content: 10",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    void batchSocsTest() throws Exception {
        String url = "/api/socks/batch";

        ClassPathResource classPathResource = new ClassPathResource("test1.csv");
        InputStream inputStream = classPathResource.getInputStream();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                classPathResource.getFilename(),
                MediaType.TEXT_PLAIN_VALUE,
                inputStream
        );

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart(url.formatted(1))
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .content(objectMapper.writeValueAsString(sockDto))
                        .characterEncoding("UTF-8")

                ).andExpect(status().is(200))
                .andReturn();

        Assertions.assertEquals(
                "The file provided is successfully processed. Parties of socks: 4 are added to the database",
                mvcResult.getResponse().getContentAsString());
    }
}