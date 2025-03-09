package by.sadovnick.datastorekafkaredis.web.dto;

import by.sadovnick.datastorekafkaredis.model.MeasurementType;
import by.sadovnick.datastorekafkaredis.model.Summary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class SummaryDto {

    private long sensorId;
    private Map<MeasurementType, List<Summary.SummaryEntry>> values;
}
