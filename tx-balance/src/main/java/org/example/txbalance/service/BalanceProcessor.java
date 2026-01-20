package org.example.txbalance.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.txbalance.entity.Balance;
import org.example.txbalance.repository.BalanceRepository;
import org.example.txbalance.repository.LastOffsetRepository;
import org.example.txcommon.balance.BalanceUpdateRequestDto;
import org.example.txbalance.entity.LastOffset;
import org.example.txcommon.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceProcessor {

    private final BalanceRepository balanceRepository;
    private final LastOffsetRepository lastOffsetRepository;

    @Transactional(transactionManager = "transactionManager")
    public void processUpdate(ConsumerRecord<String, String> record) {

        var dto = CommonUtil.commonMapper.readValue(record.value(), BalanceUpdateRequestDto.class);

        Balance fromBalance = balanceRepository.findById(dto.getFromAccountId())
                .orElseThrow();

        fromBalance.withdraw(dto.getAmount());

        Balance toBalance = balanceRepository.findById(dto.getToAccountId())
                .orElseThrow();

        toBalance.deposit(dto.getAmount());

        lastOffsetRepository.save(LastOffset.of(record.topic() + "-" + record.partition(), record.offset()));

    }


}
