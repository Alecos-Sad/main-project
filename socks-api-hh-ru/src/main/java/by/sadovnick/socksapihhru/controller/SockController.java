package by.sadovnick.socksapihhru.controller;

import by.sadovnick.socksapihhru.dto.SockDto;
import by.sadovnick.socksapihhru.service.impl.SockServiceImpl;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SockServiceImpl sockService;

    @PostMapping(value = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> income(@NotNull @RequestBody SockDto sockDto) {
        String response = sockService.arrivalSock(sockDto);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> outcome(@NotNull @RequestBody SockDto sockDto) {
        String response = sockService.departureSock(sockDto);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateSock(@NotNull @PathVariable Long id, @NotNull @RequestBody SockDto sockDto) {
        String response = sockService.updateSock(id, sockDto);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/batch")
    public ResponseEntity<String> batchSock(@NotNull @RequestParam("file") MultipartFile multipartFile) {
        String response = sockService.parseAndSaveSocks(multipartFile);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Long> searchSocks(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) @Min(0) @Max(100) Integer cottonPercentage,
            @RequestParam(required = false) String operators,
            @RequestParam(required = false) @Min(0) @Max(100) Integer cottonPercentageMin,
            @RequestParam(required = false) @Min(0) @Max(100) Integer cottonPercentageMax
    ) {
        Long count = sockService.searchSocks(
                color,
                cottonPercentage,
                operators,
                cottonPercentageMin,
                cottonPercentageMax
        );
        System.out.println(count);
        return ResponseEntity.ok(count);
    }
}
