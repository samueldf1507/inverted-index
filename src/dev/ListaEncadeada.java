package dev;

public class ListaEncadeada {

    private NoLista inicio;

    public ListaEncadeada() {
            this.inicio = null;
        }
        public void inserir(int valor) {
            NoLista novoNo = new NoLista(valor);
            if (inicio == null) {
                inicio = novoNo;
            } else {
                NoLista atual = inicio;
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novoNo;
            }
        }
        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            NoLista atual = inicio;
            while (atual != null) {
                sb.append(atual.dado);
                if (atual.proximo != null) {
                    sb.append(", ");
                }
                atual = atual.proximo;
            }
            return sb.toString();
        }

}
