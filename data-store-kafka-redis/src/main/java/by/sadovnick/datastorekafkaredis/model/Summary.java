package by.sadovnick.datastorekafkaredis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Summary {

    private long sensorId;
    private Map<MeasurementType, List<SummaryEntry>> values;

    public Summary() {
        this.values = new HashMap<>();
    }

    public void addValue(MeasurementType type, SummaryEntry value) {
        if (this.values.containsKey(type)) {
            List<SummaryEntry> entries = new ArrayList<>(values.get(type));
            entries.add(value);
            values.put(type, entries);
        } else {
            values.put(type, List.of(value));
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class SummaryEntry {
        private SummaryType type;
        private double value;
        private long counter;
    }
}
