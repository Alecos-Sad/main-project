package by.sadovnick.dataanalyserkafka.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;

    private final XML setting;

    @Bean
    public Map<String, Object> receiverProperties() {
        Map<String, Object> props = new HashMap<>(5);
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                servers
        );
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                new TextXPath(
                        this.setting,
                        "//groupId"
                ).toString()
        );
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                new TextXPath(
                        this.setting,
                        "//keyDeserializer"
                ).toString()
        );
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                new TextXPath(
                        this.setting,
                        "//valueDeserializer"
                ).toString()
        );
        props.put(
                "spring.json.trusted.packages",
                new TextXPath(
                        this.setting,
                        "//trustedPackages"
                ).toString()
        );
        return props;
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions
                .create(receiverProperties());
        return receiverOptions
                .subscription(topics)
                .addAssignListener(
                        partitions -> log.debug("assigned partitions: {}", partitions)
                )
                .addRevokeListener(
                        partitions -> log.debug("revoked partitions: {}", partitions)
                );
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(ReceiverOptions<String, Object> receiverOptions) {
        return KafkaReceiver.create(receiverOptions);
    }
}
