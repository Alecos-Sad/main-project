package by.sadovnick.datageneratekafka.constants;

public enum TopicName {

    DATA_TEMPERATURE("data-temperature"),
    DATA_VOLTAGE("data-voltage"),
    DATA_POWER("data-power");

    TopicName(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
