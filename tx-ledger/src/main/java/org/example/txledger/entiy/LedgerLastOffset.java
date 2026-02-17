package org.example.txledger.entiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LedgerLastOffset {

    @Id
    @Column(name = "topic_partition")
    private String topicPartition;

    @Column(name = "last_offset")
    private Long lastOffset;

    @Column(name = "tid")
    private String tid;

    public static LedgerLastOffset of(String topicPartition, Long offset, String tid) {
        return new LedgerLastOffset(topicPartition, offset, tid);
    }
}
