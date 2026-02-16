package org.example.txledger.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.txcommon.util.CommonUtil;
import org.example.txledger.dto.LedgerRequested;
import org.example.txledger.service.LedgerProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LedgerUpdateRequestConsumer {

    private final LedgerProcessor ledgerProcessor;

    private String successTopic;

    @KafkaListener(topics = "${kafka.topic.ledger-update-request}", groupId = "tx-ledger", concurrency = "3", containerFactory = "containerFactory")
    public void ledgerRequest(ConsumerRecord<String, String> record) {

        log.info("consumed. topic : {}, key : {}, value : {}", record.topic(), record.key(), record.value());

        var ledgerRequested = CommonUtil.commonMapper.readValue(record.value(), LedgerRequested.class);
        ledgerProcessor.updateLedger(ledgerRequested);

//        balanceProducer.produce(successTopic, new ProducerRecord<>(successTopic, payload.getTid()));

    }

}
