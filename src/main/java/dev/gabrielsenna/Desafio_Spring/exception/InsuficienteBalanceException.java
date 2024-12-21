package dev.gabrielsenna.Desafio_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsuficienteBalanceException extends PicPayException{
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insuficiente balance");
        pb.setDetail("Cannot transfer a value bigger than current balance");

        return pb;
    }
}
