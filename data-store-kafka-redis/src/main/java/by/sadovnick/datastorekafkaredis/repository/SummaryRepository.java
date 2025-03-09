package by.sadovnick.datastorekafkaredis.repository;

import by.sadovnick.datastorekafkaredis.model.Data;
import by.sadovnick.datastorekafkaredis.model.MeasurementType;
import by.sadovnick.datastorekafkaredis.model.Summary;
import by.sadovnick.datastorekafkaredis.model.SummaryType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SummaryRepository {

    Optional<Summary> findBySensorsId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

    void handle(Data data);
}
