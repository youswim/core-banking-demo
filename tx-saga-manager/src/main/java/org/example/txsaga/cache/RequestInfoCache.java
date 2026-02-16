package org.example.txsaga.cache;

import org.example.txsaga.dto.TransferTxOutbox;

public interface RequestInfoCache {

    void save(TransferTxOutbox requestInfo);

    TransferTxOutbox select(String tid);
}
