package org.example.txcommon.balance;

public record BalanceRollbackRequested(
        String tid,
        String fromAccountId,
        String toAccountId,
        Long amount) {}
