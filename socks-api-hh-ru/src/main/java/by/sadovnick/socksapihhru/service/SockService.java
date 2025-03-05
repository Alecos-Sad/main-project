package by.sadovnick.socksapihhru.service;

import by.sadovnick.socksapihhru.dto.SockDto;
import org.springframework.web.multipart.MultipartFile;

public interface SockService {

    String arrivalSock(SockDto sockDto);

    String departureSock(SockDto sockDto);

    String updateSock(Long id, SockDto sockDto);

    String parseAndSaveSocks(MultipartFile multipartFile);

    Long searchSocks(
            String color,
            Integer cottonPercentage,
            String operator,
            Integer cottonPercentageMin,
            Integer cottonPercentageMax);
}
