package by.sadovnick.socksapihhru.service.impl;

import by.sadovnick.socksapihhru.dto.SockDto;
import by.sadovnick.socksapihhru.exceptions.NotFoundSocksException;
import by.sadovnick.socksapihhru.mapper.SockMapper;
import by.sadovnick.socksapihhru.model.Sock;
import by.sadovnick.socksapihhru.repository.SockRepository;
import by.sadovnick.socksapihhru.service.SockService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SockServiceImpl implements SockService {

    private final SockRepository sockRepository;
    private final SockMapper sockMapper;

    @Override
    public String arrivalSock(SockDto sockDto) {
        String responseMessage;
        Optional<Sock> optionalSock =
                sockRepository.findByColorAndCottonPercentage(sockDto.getColor(), sockDto.getCottonPercentage());
        if (optionalSock.isPresent()) {
            int quantity = optionalSock.get().getQuantity() + sockDto.getQuantity();
            optionalSock.get().setQuantity(quantity);
            sockRepository.save(optionalSock.get());
            responseMessage = "Colors: %s and cotton content %d, found in the database, added quantity: %d, final amount: %d"
                    .formatted(sockDto.getColor(), sockDto.getCottonPercentage(), sockDto.getQuantity(), quantity);
        } else {
            sockRepository.save(sockMapper.dtoToEntity(sockDto));
            responseMessage = "Colors: %s and cotton content %d, not found in the database, added default parameters"
                    .formatted(sockDto.getColor(), sockDto.getCottonPercentage());
        }
        return responseMessage;
    }

    @Override
    public String departureSock(SockDto sockDto) {
        Optional<Sock> optionalSock =
                sockRepository.findByColorAndCottonPercentage(sockDto.getColor(), sockDto.getCottonPercentage());
        if (optionalSock.isEmpty()) {
            throw new NotFoundSocksException("The type of transferred data was not found");
        }
        if (optionalSock.get().getQuantity() < sockDto.getQuantity()) {
            throw new NotFoundSocksException("Not enough quantity found");
        }
        int quantity = optionalSock.get().getQuantity() - sockDto.getQuantity();
        optionalSock.get().setQuantity(quantity);
        sockRepository.save(optionalSock.get());
        return "Colors: %s and cotton content %d, found in the database, removed quantity: %d, final amount: %d"
                .formatted(sockDto.getColor(), sockDto.getCottonPercentage(), sockDto.getQuantity(), quantity);
    }

    @Override
    public String updateSock(Long id, SockDto sockDto) {

        Optional<Sock> byId = sockRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundSocksException("Sock by id:%d not found".formatted(id));
        }

        SockDto after = sockMapper.entityToDto(byId.get());
        byId.get().setQuantity(sockDto.getQuantity());
        byId.get().setColor(sockDto.getColor());
        byId.get().setCottonPercentage(sockDto.getCottonPercentage());

        sockRepository.save(byId.get());
        return "Colors: %s and cotton content %d, found in the database by id: %d, and updates successfully by new value color: %s and cotton content: %d"
                .formatted(after.getColor(), after.getCottonPercentage(), id, sockDto.getColor(), sockDto.getCottonPercentage());
    }

    @Override
    public String parseAndSaveSocks(MultipartFile multipartFile) {
        if (!isCVSFile(multipartFile)) {
            throw new NotFoundSocksException("File not found");
        }

        Map<String, SockDto> sockDtoMap = new HashMap<>();
        int errorCount = 0;

        try (InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream())) {
            CSVParser csvParser = new CSVParser(
                    reader,
                    CSVFormat.DEFAULT.builder()
                            .setHeader("color", "cottonPercentage", "quantity")
                            .setSkipHeaderRecord(true)
                            .setIgnoreHeaderCase(true)
                            .setTrim(true)
                            .build()
            );

            for (CSVRecord csvRecord : csvParser) {
                String color = csvRecord.get("color");
                String cottonPercentageStr = csvRecord.get("cottonPercentage");
                String quantityStr = csvRecord.get("quantity");

                if (color == null || cottonPercentageStr == null || quantityStr == null
                        || color.isEmpty() || cottonPercentageStr.isEmpty() || quantityStr.isEmpty()) {
                    errorCount++;
                    continue;
                }

                int cottonPercentage = Integer.parseInt(cottonPercentageStr);
                int quantity = Integer.parseInt(quantityStr);

                String key = color.toLowerCase() + "-" + cottonPercentage;
                SockDto exictingSockDto = sockDtoMap.get(key);

                if (exictingSockDto == null) {
                    sockDtoMap.put(key,
                            SockDto.builder()
                                    .color(color)
                                    .cottonPercentage(cottonPercentage)
                                    .quantity(quantity)
                                    .build());
                } else {
                    exictingSockDto.setQuantity(exictingSockDto.getQuantity() + quantity);
                }
            }
        } catch (IOException e) {
            throw new NotFoundSocksException(e.getMessage());
        }

        List<SockDto> sockDtos = new ArrayList<>();

        for (SockDto sockDto : sockDtoMap.values()) {
            Optional<Sock> sock = sockRepository.findByColorAndCottonPercentage(sockDto.getColor(), sockDto.getCottonPercentage());
            if (sock.isPresent()) {
                sock.get().setQuantity(sock.get().getQuantity() + sockDto.getQuantity());
            } else {
                sockDtos.add(sockDto);
            }
        }

        sockRepository.saveAll(sockMapper.dtoListToEntityList(sockDtos));
        return ("The file provided is successfully processed. Parties of socks: %d are added to the database." +
                "Errors is csv file: %d")
                .formatted(sockDtos.size(), errorCount);
    }

    @Override
    public Long searchSocks(
            String color,
            Integer cottonPercentage,
            String operator,
            Integer cottonPercentageMin,
            Integer cottonPercentageMax) {


        if (operator != null &&
                switch (operator) {
                    case "moreThan", "lessThan", "equal" -> false;
                    default -> true;
                }) {
            throw new NotFoundSocksException("Operator:%s not found".formatted(operator));
        }

        if ((cottonPercentageMin == null || cottonPercentageMax == null) && cottonPercentage == null) {
            throw new NotFoundSocksException("Cotton percentage value is null");
        }

        return sockRepository.sumQuantityByFilter(
                color,
                cottonPercentage,
                operator,
                cottonPercentageMin,
                cottonPercentageMax
        ).orElse(0L);
    }

    private boolean isCVSFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        return Objects.requireNonNull(fileName).endsWith(".csv");
    }
}