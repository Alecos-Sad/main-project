package by.sadovnick.datageneratekafka.service;

import by.sadovnick.datageneratekafka.model.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import static by.sadovnick.datageneratekafka.constants.TopicName.*;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final KafkaSender<String, Object> sender;

    @Override
    public void send(Data data) {

        String topic =
                switch (data.getMeasurementType()) {
                    case TEMPERATURE -> DATA_TEMPERATURE.getValue();
                    case VOLTAGE -> DATA_VOLTAGE.getValue();
                    case POWER -> DATA_POWER.getValue();
                };

        sender.send(
                Mono.just(
                        SenderRecord.create(
                                topic,
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(data.hashCode()), //используем хзш в качестве ключа для простоты
                                data,
                                null
                        )
                )
        ).subscribe();
    }
}
