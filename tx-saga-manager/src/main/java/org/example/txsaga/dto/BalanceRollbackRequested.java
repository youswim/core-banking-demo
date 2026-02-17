package org.example.txsaga.dto;


public record BalanceRollbackRequested(
        String tid,
        String fromAccountId,
        String toAccountId,
        Long amount) {}
