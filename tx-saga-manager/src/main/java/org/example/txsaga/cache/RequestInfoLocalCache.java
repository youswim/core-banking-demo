package org.example.txsaga.cache;

import org.example.txsaga.dto.TransferTxOutbox;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestInfoLocalCache implements RequestInfoCache{

    private final Map<String, TransferTxOutbox> cache = new HashMap<>();

    @Override
    public void save(TransferTxOutbox requestInfo) {
        cache.put(requestInfo.getTid(), requestInfo);
    }

    @Override
    public TransferTxOutbox select(String tid) {
        return cache.get(tid);
    }


}
