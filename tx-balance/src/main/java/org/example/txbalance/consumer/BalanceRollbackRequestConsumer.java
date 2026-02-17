package org.example.txbalance.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.txbalance.producer.BalanceProducer;
import org.example.txbalance.service.BalanceProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceRollbackRequestConsumer {

    private final BalanceProcessor balanceProcessor;

    @KafkaListener(topics = "${kafka.topic.balance-update-rollback}", groupId = "tx-balance", concurrency = "3", containerFactory = "containerFactory")
    public void processTxRequest(ConsumerRecord<String, String> record) {

        log.info("consumed. topic : {}, key : {}, value : {}", record.topic(), record.key(), record.value());

        balanceProcessor.processRollback(record);

//        balanceProducer.produce(successTopic, new ProducerRecord<>(successTopic, payload.getTid()));

    }

}
