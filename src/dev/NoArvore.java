package dev;

public class NoArvore {
    Palavra palavra;
    dev.NoArvore esquerda;
    dev.NoArvore direita;

    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }

}
