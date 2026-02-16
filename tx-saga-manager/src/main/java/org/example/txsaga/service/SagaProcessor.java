package org.example.txsaga.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.txcommon.balance.BalanceUpdateRequestDto;
import org.example.txsaga.cache.RequestInfoCache;
import org.example.txsaga.dto.BalanceSucceeded;
import org.example.txsaga.dto.LedgerRequested;
import org.example.txsaga.producer.SagaProducer;
import org.example.txsaga.dto.TransferTxOutbox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SagaProcessor {

    @Value("${kafka.topic.balance-update-request}")
    private String balanceUpdateRequestTopic;

    @Value("${kafka.topic.ledger-update-request}")
    private String ledgerUpdateRequestTopic;

    private final SagaProducer sagaProducer;
    private final RequestInfoCache requestInfoCache;

    public void processTransfer(TransferTxOutbox dto) {

        BalanceUpdateRequestDto burDto = new BalanceUpdateRequestDto(dto.getTid(), dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount());
        sagaProducer.produce(balanceUpdateRequestTopic, new ProducerRecord<>(balanceUpdateRequestTopic, burDto.getFromAccountId(), burDto));

    }

    public void updateLedger(BalanceSucceeded balanceSucceeded) {

        TransferTxOutbox transferRequested = requestInfoCache.select(balanceSucceeded.tid());
        LedgerRequested ledgerRequested = new LedgerRequested(transferRequested.getTid(), transferRequested.getFromAccountId(), transferRequested.getToAccountId(), transferRequested.getAmount());

        sagaProducer.produce(ledgerUpdateRequestTopic, new ProducerRecord<>(ledgerUpdateRequestTopic, ledgerRequested.fromAccountId(), ledgerRequested));

    }


}
