package dev.gabrielsenna.Desafio_Spring.repository;

import dev.gabrielsenna.Desafio_Spring.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
