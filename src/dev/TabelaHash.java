package dev;

import java.util.ArrayList;
import java.util.List;

public class TabelaHash {
    private ArvoreBinariaBusca[] tabela;
    private int tamanho;

    public TabelaHash() {
        this.tamanho = 26; // 26 letras do alfabeto
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    private int funcaoHash(String palavra) {
        char primeiraLetra = palavra.toLowerCase().charAt(0);
        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        }
        return 0; // Para caracteres não alfabéticos
    }

    public void inserir(Palavra palavra) {
        int indice = funcaoHash(palavra.getPalavra());
        tabela[indice].inserir(palavra);
    }

    public Palavra buscar(String palavraBusca) {
        int indice = funcaoHash(palavraBusca);
        return tabela[indice].buscar(palavraBusca);
    }

    public List<Palavra> obterTodasPalavrasOrdenadas() {
        List<Palavra> todasPalavras = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            tabela[i].percorrerEmOrdem(todasPalavras);
        }
        return todasPalavras;
    }
}