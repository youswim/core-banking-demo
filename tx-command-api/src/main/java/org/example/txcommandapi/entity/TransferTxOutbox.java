package org.example.txcommandapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.txcommandapi.dto.TransferRequestDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TransferTxOutbox {

    @Id
    private String tid;

    private String fromAccountId;

    private String toAccountId;

    private Long amount;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime expireAt;

    @PrePersist
    private void prePersist() {
        if (expireAt == null) {
            this.expireAt = LocalDateTime.now().plusSeconds(5);
        }
    }

    public static TransferTxOutbox of(TransferRequestDto dto) {
        return new TransferTxOutbox(dto.getTid(), dto.getFromAccountId(), dto.getToAccountId(), dto.getAmount(), null, null);
    }
}

