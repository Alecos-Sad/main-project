package by.sadovnick.redisjava.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Car")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private Integer year;

}
