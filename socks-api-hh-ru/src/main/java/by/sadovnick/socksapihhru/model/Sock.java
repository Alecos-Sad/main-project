package by.sadovnick.socksapihhru.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sock_table")
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "Color field cannot be null")
    private String color;

    @Column
    @NotNull(message = "Cotton percentage field must not be null")
    @Min(value = 0, message = "The percentage of cotton cannot be less than 0")
    @Max(value = 100, message = "The percentage of cotton cannot be greater than 100")
    private Integer cottonPercentage;

    @Column
    @NotNull(message = "Quantity field must not be null")
    @Min(value = 0, message = "The quantity of socks cannot be less than 0")
    private Integer quantity;

}
