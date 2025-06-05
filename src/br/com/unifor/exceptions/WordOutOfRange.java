package br.com.unifor.exceptions;

public class WordOutOfRange extends RuntimeException {
    public WordOutOfRange(String word) {
        super("Palavra fora do intervalo esperado");
    }
}
