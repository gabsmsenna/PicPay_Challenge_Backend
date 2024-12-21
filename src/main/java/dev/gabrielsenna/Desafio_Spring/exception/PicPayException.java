package dev.gabrielsenna.Desafio_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PicPayException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus((HttpStatus.INTERNAL_SERVER_ERROR));

        pb.setTitle("PicPay Internal Server Error");
        return pb;
    };
}
