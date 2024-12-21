package dev.gabrielsenna.Desafio_Spring.service;

import dev.gabrielsenna.Desafio_Spring.controller.dto.TransferDto;
import dev.gabrielsenna.Desafio_Spring.entity.Transfer;
import dev.gabrielsenna.Desafio_Spring.entity.Wallet;
import dev.gabrielsenna.Desafio_Spring.exception.InsuficienteBalanceException;
import dev.gabrielsenna.Desafio_Spring.exception.TransferNotAllowedForWalletException;
import dev.gabrielsenna.Desafio_Spring.exception.TransferNotAuthorizedException;
import dev.gabrielsenna.Desafio_Spring.exception.WalletNotFoundException;
import dev.gabrielsenna.Desafio_Spring.repository.TransferRepository;
import dev.gabrielsenna.Desafio_Spring.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final NotificationService notificationService;

    private final AuthorizationService authorizationService;

    private final TransferRepository transferRepository;

    private WalletRepository walletRepository;

    public TransferService(NotificationService notificationService, AuthorizationService authorizationService, TransferRepository transferRepository, WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender){

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletException();
        }

        if(!sender.isBalanceEqualOrGreaterThan(transferDto.value())) {
            throw new InsuficienteBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
