package by.sadovnick.datageneratekafka.web.controller;

import by.sadovnick.datageneratekafka.model.Data;
import by.sadovnick.datageneratekafka.model.test.DataTestOptions;
import by.sadovnick.datageneratekafka.model.test.DataTestOptionsDto;
import by.sadovnick.datageneratekafka.service.KafkaDataService;
import by.sadovnick.datageneratekafka.service.test.TestDataService;
import by.sadovnick.datageneratekafka.web.dto.DataDto;
import by.sadovnick.datageneratekafka.web.mapper.DataMapper;
import by.sadovnick.datageneratekafka.web.mapper.DataTestOptionsMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    private final KafkaDataService kafkaDataService;
    private final TestDataService testDataService;

    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    public DataController(DataMapper dataMapper, KafkaDataService kafkaDataService,
                          TestDataService testDataService, DataTestOptionsMapper dataTestOptionsMapper) {
        this.dataMapper = dataMapper;
        this.kafkaDataService = kafkaDataService;
        this.testDataService = testDataService;
        this.dataTestOptionsMapper = dataTestOptionsMapper;
    }

    @PostMapping("/send")
    public void send(@RequestBody DataDto dataDto) {
        Data data = dataMapper.toEntity(dataDto);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto testOptionsDto) {
        DataTestOptions testOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataService.sendMessage(testOptions);
    }

}
