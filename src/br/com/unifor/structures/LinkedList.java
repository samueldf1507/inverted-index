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

    public void addAtAnyPosition(int element, int position) {
        if (position < 0 || position > this.numberOfElements) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        if (position == 0) {
            Node newNode = new Node(element);
            if (this.first == null) {
                this.last = newNode;
                this.first = newNode;
            } else {
                newNode.setNext(newNode);
                this.first = newNode;
            }
            this.numberOfElements++;
        } else if (position == numberOfElements) {
            Node newNode = new Node(element);
            if (this.last == null) {
                this.first = newNode;
                this.last = newNode;
            } else {
                this.last.setNext(newNode);
                this.last = newNode;
            }
            this.numberOfElements++;

        } else {
            Node newNode = new Node(element);
            Node currentlyNode = this.first;
            for (int i = 0; i < position - 1; i++) {
                currentlyNode = currentlyNode.getNext();
            }
            newNode.setNext(currentlyNode.getNext());
            currentlyNode.setNext(newNode);
            this.numberOfElements++;
        }
    }

    public void removeAtAnyPosition(int position) {
        if (position < 0 || position > this.numberOfElements) {
            System.out.println("A lista está vazia");
        }
        if (position == 0) {
            if (this.first == null) {
                System.out.println("A lista está vazia");
            } else {
                this.first = this.first.getNext();
                this.numberOfElements--;
                if (numberOfElements == 0) {
                    this.last = null;
                }
            }
        } else if (position == this.numberOfElements - 1) {
            if (this.last == null) {
                System.out.println("A lista está vazia");
            } else {
                if (this.first == this.last) {
                    this.first = null;
                    this.last = null;
                } else {
                    Node currentlyNode = this.first;
                    while (currentlyNode.getNext() != this.last) {
                        currentlyNode = currentlyNode.getNext();
                    }
                    this.last = currentlyNode;
                    currentlyNode.setNext(null);
                }
            }
        }
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
