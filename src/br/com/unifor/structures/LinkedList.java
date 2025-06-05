package br.com.unifor.structures;

public class LinkedList<T> {
    private Node<T> start;
    private Node<T> end;
    private int numberOfElements;

    public LinkedList() {
        this.start = null;
        this.end = null;
        this.numberOfElements = 0;
    }

    public Node<T> getStart() {
        return start;
    }

    public void printLinkedList() {
        Node<T> currentNo = this.start;
        System.out.println("Lista: ");
        while (currentNo != null) {
            System.out.print(currentNo.getElement() + " ");
            currentNo = currentNo.getNext();
        }
    }

    public boolean contains(T element) {
        Node<T> currentNode = this.start;
        while(currentNode != null) {
            if (currentNode.getElement().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public void add(T element) {
        if (!contains(element)) {
            Node<T> newNode = new Node<>(element);
            if (this.start == null) {
                this.start = newNode;
                this.end = newNode;
            } else {
                this.end.setNext(newNode);
                this.end = newNode;
            }
            this.numberOfElements++;
        }
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = this.start;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(" ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }

}


