package br.com.unifor.structures;

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
            return currentNodeABB;
        }
        return currentNodeABB;
    }

    private Word searchRecursive(NodeABB currentNodeABB, String word) {
        if (currentNodeABB == null) {
            return null;
        }

        int comparation = word.compareTo(currentNodeABB.getElement().getWord());

        if (comparation == 0) {
            return currentNodeABB.getElement();
        } else if (comparation < 0) {
            return  searchRecursive(currentNodeABB.getLeft(), word);
            
        } else {
            return searchRecursive(currentNodeABB.getRight(), word);
        }

    }

    private void printInRecursiveOrder(NodeABB currentNodeABB) {
        if (currentNodeABB != null) {
            printInRecursiveOrder(currentNodeABB.getLeft());
            System.out.println(currentNodeABB.getElement());
            printInRecursiveOrder(currentNodeABB.getRight());
        }
    }

    public void insert(Word word) {
        root = insertRecursive(root, word);
    }

    public Word search(String word) {
        return searchRecursive(root, word);
    }

    public void printInOrder() {
        printInRecursiveOrder(root);
    }
}
