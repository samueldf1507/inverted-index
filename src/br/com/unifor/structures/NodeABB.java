// br.com.unifor.structures/ABBNode.java
package br.com.unifor.structures;

public class NodeABB { // Para a ABB
    public Word element; // Armazena o objeto Word
    public NodeABB esquerdo;
    public NodeABB direito;

    public NodeABB(Word element) {
        this.element = element;
        this.esquerdo = null;
        this.direito = null;
    }

    public Word getElement() { return element; }
    public void setElement(Word element) { this.element = element; }
    public NodeABB getEsquerdo() { return esquerdo; }
    public void setEsquerdo(NodeABB esquerdo) { this.esquerdo = esquerdo; }
    public NodeABB getDireito() { return direito; }
    public void setDireito(NodeABB direito) { this.direito = direito; }
}