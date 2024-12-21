package dev.gabrielsenna.Desafio_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreayExistsException extends PicPayException {

    public String detail;

    public WalletDataAlreayExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet Data Already Exists");
        pb.setDetail(detail);

        return pb;
    }
}
