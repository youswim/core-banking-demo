package org.example.txsaga.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.txsaga.dto.TransferTxOutbox;
import org.example.txcommon.util.*;
import org.example.txsaga.service.SagaProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TxRequestConsumer {

    private final SagaProcessor sagaProcessor;

    @KafkaListener(topics = "${kafka.topic.tx-request}", groupId = "tx-saga-manager", concurrency = "3", containerFactory = "containerFactory")
    public void processTxRequest(ConsumerRecord<String, String> record) {

        log.info("consumed. topic : {}, key : {}, value : {}", record.topic(), record.key(), record.value());

        var payload = CommonUtil.commonMapper.readValue(record.value(), TransferTxOutbox.class);
        sagaProcessor.processTransfer(payload);

    }

}
