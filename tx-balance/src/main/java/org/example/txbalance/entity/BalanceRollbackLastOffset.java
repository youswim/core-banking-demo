package org.example.txbalance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BalanceRollbackLastOffset {

    @Id
    @Column(name = "topic_partition")
    private String topicPartition;

    @Column(name = "last_offset")
    private Long lastOffset;

    @Column(name = "tid")
    private String tid;

    public static BalanceRollbackLastOffset of(String topicPartition, Long offset, String tid) {
        return new BalanceRollbackLastOffset(topicPartition, offset, tid);
    }
}
