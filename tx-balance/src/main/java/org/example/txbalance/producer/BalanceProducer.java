package org.example.txbalance.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produce(String topic, ProducerRecord<String, Object> producerRecord) {

        kafkaTemplate.send(producerRecord).whenCompleteAsync((res, ex) -> {
            if (ex == null) {
                log.info("send success. topic : {}, res : {}", topic, res);
                return;
            }
            log.error("send failed. topic : {}, payload : {}", topic, producerRecord, ex);
        });
    }

}
