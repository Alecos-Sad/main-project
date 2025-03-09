package by.sadovnick.datastorekafkaredis.web.service;

import by.sadovnick.datastorekafkaredis.model.Data;
import by.sadovnick.datastorekafkaredis.model.MeasurementType;
import by.sadovnick.datastorekafkaredis.model.Summary;
import by.sadovnick.datastorekafkaredis.model.SummaryType;

import java.util.Set;

public interface SummaryService {

    Summary get(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

    void handle(Data data);
}
