package by.sadovnick.datastorekafkaredis.web.service.impl;

import by.sadovnick.datastorekafkaredis.exception.SensorNotFoundException;
import by.sadovnick.datastorekafkaredis.model.Data;
import by.sadovnick.datastorekafkaredis.model.MeasurementType;
import by.sadovnick.datastorekafkaredis.model.Summary;
import by.sadovnick.datastorekafkaredis.model.SummaryType;
import by.sadovnick.datastorekafkaredis.repository.SummaryRepository;
import by.sadovnick.datastorekafkaredis.web.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        return summaryRepository.findBySensorsId(
                sensorId,
                measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes

        ).orElseThrow(SensorNotFoundException::new);
    }

    @Override
    public void handle(Data data) {
        summaryRepository.handle(data);
    }
}
