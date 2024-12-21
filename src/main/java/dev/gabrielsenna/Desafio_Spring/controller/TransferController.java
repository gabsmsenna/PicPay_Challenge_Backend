package dev.gabrielsenna.Desafio_Spring.controller;

import dev.gabrielsenna.Desafio_Spring.controller.dto.TransferDto;
import dev.gabrielsenna.Desafio_Spring.entity.Transfer;
import dev.gabrielsenna.Desafio_Spring.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {
        var response = transferService.transfer(dto);

        return ResponseEntity.ok(response);
    }
}
