package by.sadovnick.dataanalyserkafka.service;

import by.sadovnick.dataanalyserkafka.model.Data;

public interface KafkaDataService {

    void handle(Data data);
}
