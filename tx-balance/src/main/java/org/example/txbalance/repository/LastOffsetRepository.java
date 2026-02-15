package org.example.txbalance.repository;

import org.example.txbalance.entity.BalanceLastOffset;
import org.springframework.data.repository.CrudRepository;

public interface LastOffsetRepository extends CrudRepository<BalanceLastOffset, String> {
}
