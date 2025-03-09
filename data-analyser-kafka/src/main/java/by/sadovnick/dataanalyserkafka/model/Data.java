package by.sadovnick.dataanalyserkafka.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "data")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sensorId;

    private LocalDateTime timestamp;

    private double measurement;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;

    public enum MeasurementType {
        TEMPERATURE,
        VOLTAGE,
        POWER
    }
}
