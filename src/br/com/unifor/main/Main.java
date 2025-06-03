package br.com.unifor.main;

import br.com.unifor.structures.DynamicQueue;
import br.com.unifor.structures.LinkedList;
import br.com.unifor.structures.Word;

public class Main {
    public static void main(String[] args) {
        DynamicQueue dynamicQueue = new DynamicQueue();
        Word word = new Word("Ola");
        Word word2 = new Word("Olaa");
        Word word3 = new Word("Olaaa");
        dynamicQueue.enqueue(word);
        dynamicQueue.enqueue(word2);
        dynamicQueue.enqueue(word3);
        dynamicQueue.print();

    }
}
