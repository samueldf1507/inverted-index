package br.com.unifor.structures;

import java.io.PrintWriter; // Importe a classe PrintWriter

public class BinarySearchTree {
    private NodeABB root;

    public BinarySearchTree() {
        this.root = null;
    }

    private NodeABB insertRecursive(NodeABB currentNodeABB, Word word) {
        if (currentNodeABB == null) {
            return new NodeABB(word);
        }

        int comparation = word.getWord().compareTo(currentNodeABB.getElement().getWord());

        if (comparation < 0) {
            currentNodeABB.setLeft(insertRecursive(currentNodeABB.getLeft(), word));
        } else if (comparation > 0) {
            currentNodeABB.setRight(insertRecursive(currentNodeABB.getRight(), word));
        } else {
            if (word.getOcurrences().getNumberOfElements() > 0) {
                if (word.getOcurrences().getStart() != null) {
                    currentNodeABB.getElement().addOcurrence((Integer) word.getOcurrences().getStart().getElement());
                }
            }
            return currentNodeABB;
        }
        return currentNodeABB;
    }

    public void insert(Word word) {
        root = insertRecursive(root, word);
    }

    private Word searchRecursive(NodeABB currentNodeABB, String wordTextToSearch) {
        if (currentNodeABB == null) {
            return null;
        }

        int comparation = wordTextToSearch.compareTo(currentNodeABB.getElement().getWord());

        if (comparation == 0) {
            return currentNodeABB.getElement();
        } else if (comparation < 0) {
            return searchRecursive(currentNodeABB.getLeft(), wordTextToSearch);
        } else {
            return searchRecursive(currentNodeABB.getRight(), wordTextToSearch);
        }
    }

    public Word search(String wordTextToSearch) {
        return searchRecursive(root, wordTextToSearch);
    }

    private void printIndexToWriterRecursive(NodeABB currentNodeABB, HashTable mainHashTable, PrintWriter writer) {
        if (currentNodeABB != null) {
            printIndexToWriterRecursive(currentNodeABB.getLeft(), mainHashTable, writer);

            Word keywordWord = currentNodeABB.getElement();

            Word foundWordInMainText = mainHashTable.search(keywordWord.getWord());

            if (foundWordInMainText != null) {
                writer.println(foundWordInMainText);
            }

            printIndexToWriterRecursive(currentNodeABB.getRight(), mainHashTable, writer);
        }
    }

    public void printIndexToWriter(HashTable mainHashTable, PrintWriter writer) {
        printIndexToWriterRecursive(this.root, mainHashTable, writer);
    }

    private void printInRecursiveOrder(NodeABB currentNodeABB) {
        if (currentNodeABB != null) {
            printInRecursiveOrder(currentNodeABB.getLeft());
            System.out.println(currentNodeABB.getElement());
            printInRecursiveOrder(currentNodeABB.getRight());
        }
    }

    public void printInOrder() {
        printInRecursiveOrder(root);
    }
}