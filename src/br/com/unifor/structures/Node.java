package br.com.unifor.structures;

public class Node {
    private Word word;
    private Node next;

    public Node(Word word) {
        this.word = word;
        this.next = null;
    }

    public Word getWord() {

        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
