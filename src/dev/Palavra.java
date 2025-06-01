package dev;

public class Palavra {
    private String palavra;
    private ListaEncadeada ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.ocorrencias = new ListaEncadeada();
    }

    public String getPalavra() {
        return palavra;
    }

    public ListaEncadeada getOcorrencias() {
        return ocorrencias;
    }

    public void adicionarOcorrencia(int linha) {
        ocorrencias.inserir(linha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(palavra).append(": ");
        sb.append(ocorrencias.toString());
        return sb.toString();
    }
}
