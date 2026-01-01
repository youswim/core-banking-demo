package org.example.txcommandapi.entity;

import jakarta.persistence.*;
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
    @Column(name = "tid")
    private String tid;

    @Column(name = "from_account_id", nullable = false)
    private String fromAccountId;

    @Column(name = "to_account_id", nullable = false)
    private String toAccountId;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expire_at", nullable = false)
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

