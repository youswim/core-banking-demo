package org.example.txcommandapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TransferRequestDto {

    @JsonProperty("tid")
    private final String tid = UUID.randomUUID().toString();

    @JsonProperty("fromAccountId")
    private String fromAccountId;

    @JsonProperty("toAccountId")
    private String toAccountId;

    @JsonProperty("amount")
    private Long amount;
}
