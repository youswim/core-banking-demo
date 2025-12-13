package org.example.txcommandapi.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TransferRequestDto {

    @Column(name = "tid")
    private final String tid = UUID.randomUUID().toString();

    @Column(name = "from_account_id")
    private String fromAccountId;

    @Column(name = "from_account_id")
    private String toAccountId;

    @Column(name = "tid")
    private Long amount;
}
