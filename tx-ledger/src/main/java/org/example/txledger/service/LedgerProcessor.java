package org.example.txledger.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.txcommon.util.CommonUtil;
import org.example.txledger.dto.LedgerRequested;
import org.example.txledger.entiy.Direction;
import org.example.txledger.entiy.Ledger;
import org.example.txledger.entiy.LedgerLastOffset;
import org.example.txledger.repository.LastOffsetRepository;
import org.example.txledger.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LedgerProcessor {

    private final LedgerRepository ledgerRepository;
    private final LastOffsetRepository lastOffsetRepository;

    @Transactional(transactionManager = "transactionManager")
    public void updateLedger(ConsumerRecord<String, String> record) {

        var ledgerRequested = CommonUtil.commonMapper.readValue(record.value(), LedgerRequested.class);

        ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.fromAccountId(), Direction.CREDIT, ledgerRequested.amount()));

        ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.toAccountId(), Direction.DEBIT, ledgerRequested.amount()));

        lastOffsetRepository.save(LedgerLastOffset.of(record.topic() + "-" + record.partition(), record.offset(), ledgerRequested.tid()));
    }

}
