package dev;

import java.util.List;

public class ArvoreBinariaBusca {

        private NoArvore raiz;

        public ArvoreBinariaBusca() {
            this.raiz = null;
        }

        public void inserir(Palavra palavra) {
            raiz = inserirRecursivo(raiz, palavra);
        }

        private NoArvore inserirRecursivo(NoArvore no, Palavra palavra) {
            if (no == null) {
                return new NoArvore(palavra);
            }

            int comparacao = palavra.getPalavra().compareTo(no.palavra.getPalavra());
            if (comparacao < 0) {
                no.esquerda = inserirRecursivo(no.esquerda, palavra);
            } else if (comparacao > 0) {
                no.direita = inserirRecursivo(no.direita, palavra);
            } else {
                // Palavra já existe, adicionar nova ocorrência
                return no;
            }

            return no;
        }

        public Palavra buscar(String palavraBusca) {
            return buscarRecursivo(raiz, palavraBusca.toLowerCase());
        }

        private Palavra buscarRecursivo(NoArvore no, String palavraBusca) {
            if (no == null) {
                return null;
            }

            int comparacao = palavraBusca.compareTo(no.palavra.getPalavra());
            if (comparacao == 0) {
                return no.palavra;
            } else if (comparacao < 0) {
                return buscarRecursivo(no.esquerda, palavraBusca);
            } else {
                return buscarRecursivo(no.direita, palavraBusca);
            }
        }

        public void percorrerEmOrdem(List<Palavra> lista) {
            percorrerEmOrdemRecursivo(raiz, lista);
        }

        private void percorrerEmOrdemRecursivo(NoArvore no, List<Palavra> lista) {
            if (no != null) {
                percorrerEmOrdemRecursivo(no.esquerda, lista);
                lista.add(no.palavra);
                percorrerEmOrdemRecursivo(no.direita, lista);
            }
        }

}
