package org.example.txbalance.service;

import lombok.RequiredArgsConstructor;
import org.example.txbalance.entity.Balance;
import org.example.txbalance.repository.BalanceRepository;
import org.example.txcommon.balance.BalanceUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceProcessor {

    private final BalanceRepository balanceRepository;

    @Transactional(transactionManager = "transactionManager")
    public void processUpdate(BalanceUpdateRequestDto dto) {

        Balance fromBalance = balanceRepository.findById(dto.getFromAccountId())
                .orElseThrow();

        System.out.println(fromBalance);

        fromBalance.withdraw(dto.getAmount());

        Balance toBalance = balanceRepository.findById(dto.getToAccountId())
                .orElseThrow();

        System.out.println(toBalance);

        toBalance.deposit(dto.getAmount());

    }


}
