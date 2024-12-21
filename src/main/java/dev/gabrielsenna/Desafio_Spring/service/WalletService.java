package dev.gabrielsenna.Desafio_Spring.service;

import dev.gabrielsenna.Desafio_Spring.WalletDataAlreayExistsException;
import dev.gabrielsenna.Desafio_Spring.controller.dto.CreateWalletDto;
import dev.gabrielsenna.Desafio_Spring.entity.Wallet;
import dev.gabrielsenna.Desafio_Spring.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto walletDto) {

        var walletExist = walletRepository.findByCpfCnpjOrEmail(walletDto.cpfCnpj(), walletDto.email());

        if (walletExist.isPresent()) {
            throw new WalletDataAlreayExistsException("Cpf/Cnpj or Email already exists");
        }

        return walletRepository.save(walletDto.toWallet());
    }
}
