package org.example.txbalance.repository;

import org.example.txbalance.entity.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, String> {
}
