package by.sadovnick.datastorekafkaredis.web.controller;

import by.sadovnick.datastorekafkaredis.model.MeasurementType;
import by.sadovnick.datastorekafkaredis.model.Summary;
import by.sadovnick.datastorekafkaredis.model.SummaryType;
import by.sadovnick.datastorekafkaredis.web.dto.SummaryDto;
import by.sadovnick.datastorekafkaredis.web.mapper.SummaryMapper;
import by.sadovnick.datastorekafkaredis.web.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final SummaryService summaryService;

    private final SummaryMapper summaryMapper;

    @GetMapping("/summary/{sensorId}")
    public SummaryDto getSummary(
            @PathVariable("sensorId") long sensorId,
            @RequestParam(value = "mt", required = false)
            Set<MeasurementType> measurementTypes,
            @RequestParam(value = "st", required = false)
            Set<SummaryType> summaryTypes
    ) {
        Summary summary = summaryService.get(
                sensorId,
                measurementTypes,
                summaryTypes
        );
        return summaryMapper.toDto(summary);
    }
}
