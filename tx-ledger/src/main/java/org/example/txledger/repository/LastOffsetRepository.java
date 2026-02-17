package org.example.txledger.repository;

import org.example.txledger.entiy.LedgerLastOffset;
import org.springframework.data.repository.CrudRepository;

public interface LastOffsetRepository extends CrudRepository<LedgerLastOffset, String> {
}
