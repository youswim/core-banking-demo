package org.example.txcommandapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.txcommandapi.dto.TransferRequestDto;
import org.example.txcommandapi.service.TxService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tx")
@RequiredArgsConstructor
public class TxCommandController {

    private final TxService txService;

    @PostMapping("/transfer")
    public void requestTransfer(@RequestHeader HttpHeaders header, @RequestBody TransferRequestDto transferRequestDto) {
        txService.saveTx(header, transferRequestDto);
    }
}
