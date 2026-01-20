package org.example.txbalance.repository;

import org.example.txbalance.entity.LastOffset;
import org.springframework.data.repository.CrudRepository;

public interface LastOffsetRepository extends CrudRepository<LastOffset, String> {
}
