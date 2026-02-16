package org.example.txledger.repository;

import org.example.txledger.entiy.Ledger;
import org.springframework.data.repository.CrudRepository;

public interface LedgerRepository extends CrudRepository<Ledger, String> {
}
