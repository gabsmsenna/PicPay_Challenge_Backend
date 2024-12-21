package dev.gabrielsenna.Desafio_Spring.service;

import dev.gabrielsenna.Desafio_Spring.client.AuthorizationClient;
import dev.gabrielsenna.Desafio_Spring.entity.Transaction;
import dev.gabrielsenna.Desafio_Spring.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transaction transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
