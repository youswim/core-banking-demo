package org.example.txledger.dto;

public record LedgerRequested(
        String tid,
        String fromAccountId,
        String toAccountId,
        Long amount) {}
