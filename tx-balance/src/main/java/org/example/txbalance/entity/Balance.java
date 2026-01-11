package org.example.txbalance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Id
    @Column(name = "balance_id")
    private String balanceId;

    @Column(name = "amount")
    private Long amount;

    public static Balance of(String balanceId) {
        return new Balance(balanceId, null);
    }

    public static Balance of(String balanceId, Long amount) {
        return new Balance(balanceId, amount);
    }

    public void withdraw(Long amount) {
        this.amount -= amount;
    }

    public void deposit(Long amount) {
        this.amount += amount;
    }

}
