package by.sadovnick.dataanalyserkafka.service.impl;

import by.sadovnick.dataanalyserkafka.model.Data;
import by.sadovnick.dataanalyserkafka.repository.DataRepository;
import by.sadovnick.dataanalyserkafka.service.KafkaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final DataRepository dataRepository;

    @Override
    public void handle(Data data) {
        log.info("Data object: {} was saved", data.toString());
        dataRepository.save(data);
    }
}
