package org.example.txcommandapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.txcommandapi.dto.TransferRequestDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TransferTxOutbox {

    @Id
    private String tid;

    private String fromAccountId;

    private String toAccountId;

    private Long amount;

    public static TransferTxOutbox of(TransferRequestDto dto) {
        return new TransferTxOutbox(dto.getTid(), dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount());
    }
}

