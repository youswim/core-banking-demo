package org.example.txsaga.dto;

public record LedgerRequested (
        String tid,
        String fromAccountId,
        String toAccountId,
        Long amount) {}
