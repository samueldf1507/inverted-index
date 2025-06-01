package br.com.unifor.structures;

public class LinkedList {
    private Node first;
    private Node last;
    private int numberOfElements;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.numberOfElements = 0;
    }

    // Métodos auxiliares, podem ser privados
    public void addFirst(int element) {
        Node newNode = new Node(element);
        if (this.first == null) { // Lista vazia
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.setNext(this.first);
            this.first = newNode;
        }
        this.numberOfElements++;
    }

    public void addLast(int element) {
        Node newNode = new Node(element);
        if (this.last == null) { // Lista vazia (também é o primeiro elemento)
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
        }
        this.numberOfElements++;
    }

    // Seu método principal de adição
    public void addAtAnyPosition(int element, int position) {
        if (position < 0 || position > this.numberOfElements) {
            throw new IndexOutOfBoundsException("Posição inválida: " + position + ", Tamanho da lista: " + this.numberOfElements);
        }

        if (position == 0) {
            addFirst(element);
        } else if (position == this.numberOfElements) { // Adicionar no final
            addLast(element);
        } else { // Adicionar no meio
            Node newNode = new Node(element);
            Node current = this.first;
            // Percorre até o nó ANTERIOR à posição de inserção
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext()); // O novo nó aponta para o próximo do current
            current.setNext(newNode); // O current aponta para o novo nó
            this.numberOfElements++;
        }
    }
    // Método auxiliar para remoção do primeiro
    public void removeFirst() {
        if (this.first == null) {
            throw new IllegalStateException("A lista está vazia. Não é possível remover.");
        }
        this.first = this.first.getNext();
        this.numberOfElements--;
        if (this.numberOfElements == 0) {
            this.last = null; // Se ficou vazia, last também é null
        }
    }

    // Seu método principal de remoção
    public void removeAtAnyPosition(int position) {
        if (position < 0 || position >= this.numberOfElements) { // Note: >= numberOfElements
            throw new IndexOutOfBoundsException("Posição inválida para remoção: " + position + ", Tamanho da lista: " + this.numberOfElements);
        }

        if (position == 0) {
            removeFirst();
        } else { // Remoção do meio ou do final
            Node current = this.first;
            // Percorre até o nó ANTERIOR à posição de remoção
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            // current é o nó ANTERIOR ao que será removido
            Node nodeToRemove = current.getNext(); // Este é o nó que será removido
            current.setNext(nodeToRemove.getNext()); // O nó anterior agora aponta para o próximo do nó removido

            if (nodeToRemove == this.last) { // Se o nó removido era o último
                this.last = current; // O anterior a ele agora é o novo último
            }
            this.numberOfElements--;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node currentNode = this.first;
        while (currentNode != null) {
            sb.append(currentNode.getElement());
            if (currentNode.getNext() != null) {
                sb.append(" "); // Adiciona espaço entre os números
            }
            currentNode = currentNode.getNext();
        }
        return sb.toString();
    }


    public void printLinkedList() {
        Node currentNode = this.first;
        System.out.println("Lista: ");
        while (currentNode != null) {
            System.out.print(currentNode.getElement() + " ");
            currentNode = currentNode.getNext();
        }
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}
