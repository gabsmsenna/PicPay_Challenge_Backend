package dev.gabrielsenna.Desafio_Spring.controller;

import dev.gabrielsenna.Desafio_Spring.controller.dto.CreateWalletDto;
import dev.gabrielsenna.Desafio_Spring.entity.Wallet;
import dev.gabrielsenna.Desafio_Spring.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto walletDto) {

        var wallet = walletService.createWallet(walletDto);

        return ResponseEntity.ok(wallet);
    }
}
