package dev.gabrielsenna.Desafio_Spring.controller.dto;

import dev.gabrielsenna.Desafio_Spring.entity.Wallet;
import dev.gabrielsenna.Desafio_Spring.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(
        @NotBlank String fullName,
        @NotBlank String cpfCnpj,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull WalletType.Enum walletType

) {
    public Wallet toWallet() {

        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.getWalletType()
        );
    }

}
