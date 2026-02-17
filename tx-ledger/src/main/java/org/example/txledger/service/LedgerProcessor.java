package org.example.txledger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.txcommon.util.CommonUtil;
import org.example.txledger.dto.LedgerRequested;
import org.example.txledger.entiy.Direction;
import org.example.txledger.entiy.Ledger;
import org.example.txledger.entiy.LedgerLastOffset;
import org.example.txledger.producer.LedgerProducer;
import org.example.txledger.repository.LastOffsetRepository;
import org.example.txledger.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LedgerProcessor {

    private final LedgerRepository ledgerRepository;
    private final LastOffsetRepository lastOffsetRepository;
    private final LedgerProducer ledgerProducer;

    @Value("${kafka.topic.ledger-update-failed}")
    private String ledgerFailedTopic;

    @Transactional(transactionManager = "transactionManager")
    public void updateLedger(ConsumerRecord<String, String> record) {
        try {

            var ledgerRequested = CommonUtil.commonMapper.readValue(record.value(), LedgerRequested.class);
            if (ledgerRequested.amount() == 444) { // 롤백 테스트를 위한 임의 validation 조건
                throw new IllegalArgumentException("invalid amount");
            }
            ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.fromAccountId(), Direction.CREDIT, ledgerRequested.amount()));

            ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.toAccountId(), Direction.DEBIT, ledgerRequested.amount()));

            lastOffsetRepository.save(LedgerLastOffset.of(record.topic() + "-" + record.partition(), record.offset(), ledgerRequested.tid()));

        } catch (Exception e) {
            log.error("exception while update ledger : {}", record);
            ledgerProducer.produce(ledgerFailedTopic, new ProducerRecord<>(ledgerFailedTopic, record.key(), record.value()));
        }

    }

}
