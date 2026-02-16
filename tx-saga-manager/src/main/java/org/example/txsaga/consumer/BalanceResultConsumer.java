package org.example.txsaga.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.txcommon.util.CommonUtil;
import org.example.txsaga.dto.BalanceSucceeded;
import org.example.txsaga.service.SagaProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceResultConsumer {

    private final SagaProcessor sagaProcessor;

    @KafkaListener(topics = "${kafka.topic.balance-update-success}", groupId = "tx-saga-manager", concurrency = "3", containerFactory = "containerFactory")
    public void updateLedger(ConsumerRecord<String, String> record) {

        log.info("consumed. topic : {}, key : {}, value : {}", record.topic(), record.key(), record.value());

        var payload = CommonUtil.commonMapper.readValue(record.value(), BalanceSucceeded.class);
        sagaProcessor.updateLedger(payload);

        /*
        캐시에 저장해둔 요청 정보를 조회하여 원장 update요청에 사용하면 좋을 듯
         */

    }
}
