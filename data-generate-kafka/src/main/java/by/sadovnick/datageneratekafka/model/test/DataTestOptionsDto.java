package by.sadovnick.datageneratekafka.model.test;

import by.sadovnick.datageneratekafka.model.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataTestOptionsDto {

    private int delayInSeconds;
    private Data.MeasurementType[] measurementTypes;
}
