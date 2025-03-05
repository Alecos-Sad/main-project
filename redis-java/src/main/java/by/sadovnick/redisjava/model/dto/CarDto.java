package by.sadovnick.redisjava.model.dto;

public record CarDto(
        String brand,
        String model,
        String color,
        Integer year
) {
}