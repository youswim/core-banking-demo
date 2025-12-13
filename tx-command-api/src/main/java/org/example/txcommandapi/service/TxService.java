package org.example.txcommandapi.service;

import lombok.RequiredArgsConstructor;
import org.example.txcommandapi.dto.TransferRequestDto;
import org.example.txcommandapi.entity.TransferTxOutbox;
import org.example.txcommandapi.repository.TransferTxOutboxRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TxService {

    private final TransferTxOutboxRepository transferTxOutboxRepository;

    public void saveTx(HttpHeaders httpHeaders, TransferRequestDto transferRequestDto) {

        transferTxOutboxRepository.save(TransferTxOutbox.of(transferRequestDto));

    }
}
