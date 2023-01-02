package br.com.frentecorretora.fakeatm.services;

public class ValorExcedidoException extends Exception {
    public ValorExcedidoException(String message) {
        super(message);
    }
}
