package by.sadovnick.datageneratekafka.service;

import by.sadovnick.datageneratekafka.model.Data;

public interface KafkaDataService {

    void send(Data data);
}
