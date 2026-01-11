package org.example.txbalance.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.txbalance.service.BalanceProcessor;
import org.example.txbalance.producer.*;
import org.example.txcommon.balance.BalanceUpdateRequestDto;
import org.example.txcommon.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceUpdateRequestConsumer {

    private final BalanceProcessor balanceProcessor;
    private final BalanceProducer balanceProducer;

    @Value("${kafka.topic.balance-update-success}")
    private String successTopic;

    @KafkaListener(topics = "${kafka.topic.balance-update-request}", groupId = "tx-balance", concurrency = "3", containerFactory = "containerFactory")
    public void processTxRequest(ConsumerRecord<String, String> record) {

        log.info("consumed. topic : {}, key : {}, value : {}", record.topic(), record.key(), record.value());

        var payload = CommonUtil.commonMapper.readValue(record.value(), BalanceUpdateRequestDto.class);
        balanceProcessor.processUpdate(payload);

        balanceProducer.produce(successTopic, new ProducerRecord<>(successTopic, payload.getTid()));

    }

}
