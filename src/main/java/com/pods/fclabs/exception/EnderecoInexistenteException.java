package com.pods.fclabs.exception;

public class EnderecoInexistenteException extends RuntimeException {
    public EnderecoInexistenteException() {
        super();
    }

    public EnderecoInexistenteException(String mensagem) {
        super(mensagem);
    }
}
