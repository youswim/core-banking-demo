package org.example.txbalance.repository;

import org.example.txbalance.entity.BalanceRollbackLastOffset;
import org.springframework.data.repository.CrudRepository;

public interface RollbackLastOffsetRepository extends CrudRepository<BalanceRollbackLastOffset, String> {
}
