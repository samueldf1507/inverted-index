package br.com.unifor.structures;

import br.com.unifor.exceptions.WordOutOfRange;

public class HashTable {
    private BinarySearchTree[] binarySearchTrees;
    private int size = 26;

    public HashTable() {
        this.binarySearchTrees = new BinarySearchTree[size];
        for (int i = 0; i < size; i++) {
            binarySearchTrees[i] = new BinarySearchTree();
            
        }
    }

    private int hashFunction(String word) {
        char firstCharacter = Character.toLowerCase(word.charAt(0));
        if (!Character.isLetter(firstCharacter)) return -1;
        return firstCharacter - 'a';
    }

    public void insert(Word word) {
        int x = hashFunction(word.getWord());
        if (x >= 0 && x < size) {
            binarySearchTrees[x].insert(word);
        } else {
            throw new WordOutOfRange(word.getWord());
        }
    }

    public Word search(String word) {
        int index = hashFunction(word);
        if (index >= 0 && index < size) {
            return binarySearchTrees[index].search(word);
        }
        return null;
    }

    public void printHashTable() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " ->\t");
            binarySearchTrees[i].printInOrder();
        }
    }
}
