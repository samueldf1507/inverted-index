package br.com.unifor.structures;

public class DynamicQueue {

    private Node head;
    private Node tail;
    private int size;

    public DynamicQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void print() {
        System.out.print("[");
        Node cursor = this.head;
        for (int i = 0; i < this.size; i++) {
            System.out.print(cursor.getWord() + " ");
            cursor = cursor.getNext();
        }
        if (this.size == 0) {
            System.out.println("] Head: " + this.head + ", Tail: " + this.tail);
        } else {
            System.out.println("] Head: " + this.head.getWord() + ", Tail: " + this.tail.getWord());
        }
    }

    public Word dequeue() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty. Cannot remove.");
            return null;
        }

        Node removedNode = this.head;

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = removedNode.getNext();
            removedNode.setNext(null);
        }

        this.size--;

        return removedNode.getWord();
    }

    public void enqueue(Word word) {
        Node newNode = new Node(word);

        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.setNext(newNode);
        }

        this.tail = newNode;
        this.size++;
    }

    public Word peek() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty! Cannot peek.");
            return null;
        }

        return this.head.getWord();
    }

    public boolean contains(Word word) {
        Node cursor = this.head;
        for (int i = 0; i < this.size; i++) {
            if (cursor.getWord().equals(word)) {
                return true;
            }
            cursor = cursor.getNext();
        }
        return false;
    }
}
