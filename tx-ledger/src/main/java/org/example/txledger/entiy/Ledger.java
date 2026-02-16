package org.example.txledger.entiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@SequenceGenerator(
        name = "LEDGER_SEQ_GENERATOR"
        , sequenceName = "LEDGER_SEQ"
        , allocationSize = 100
)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEDGER_SEQ_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Column(name = "tid")
    private String tid;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(name = "amount")
    private Long amount;

}
