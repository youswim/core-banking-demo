package org.example.txbalance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LastOffset {

    @Id
    @Column(name = "topic_partition")
    private String topicPartition;

    @Column(name = "last_offset")
    private Long lastOffset;

    public static LastOffset of(String topicPartition, Long offset) {
        return new LastOffset(topicPartition, offset);
    }
}
