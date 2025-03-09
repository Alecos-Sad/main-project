package by.sadovnick.datageneratekafka.service.test;

import by.sadovnick.datageneratekafka.model.test.DataTestOptions;

public interface TestDataService {

    void sendMessage(DataTestOptions testOptions);
}
