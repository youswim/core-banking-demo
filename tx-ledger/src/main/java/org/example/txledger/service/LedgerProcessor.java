package org.example.txledger.service;

import lombok.RequiredArgsConstructor;
import org.example.txledger.dto.LedgerRequested;
import org.example.txledger.entiy.Direction;
import org.example.txledger.entiy.Ledger;
import org.example.txledger.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LedgerProcessor {

    private final LedgerRepository ledgerRepository;

    @Transactional(transactionManager = "transactionManager")
    public void updateLedger(LedgerRequested ledgerRequested) {

        ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.fromAccountId(), Direction.CREDIT, ledgerRequested.amount()));

        ledgerRepository.save(new Ledger(null, ledgerRequested.tid(), ledgerRequested.toAccountId(), Direction.DEBIT, ledgerRequested.amount()));
    }

}
