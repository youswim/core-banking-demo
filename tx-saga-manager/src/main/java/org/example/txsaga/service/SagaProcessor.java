package org.example.txsaga.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.txcommon.balance.BalanceUpdateRequestDto;
import org.example.txsaga.consumer.SagaProducer;
import org.example.txsaga.dto.TransferTxOutbox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SagaProcessor {

    @Value("${kafka.topic.balance-update-request}")
    private String balanceUpdateRequestTopic;

    private final SagaProducer sagaProducer;

    public void processTransfer(TransferTxOutbox dto) {

        BalanceUpdateRequestDto burDto = new BalanceUpdateRequestDto(dto.getTid(), dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount());
        sagaProducer.produce(balanceUpdateRequestTopic, new ProducerRecord<>(balanceUpdateRequestTopic, burDto.getFromAccountId(), burDto));

        // todo : command-api 쪽에다가 consume ack 날리기

    }


}
