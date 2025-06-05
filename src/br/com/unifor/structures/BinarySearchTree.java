package br.com.unifor.structures;

import java.io.PrintWriter; // Importe a classe PrintWriter

public class BinarySearchTree {
    private NodeABB root;

    public BinarySearchTree() {
        this.root = null;
    }

    // --- Métodos de Inserção ---
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
                // Acessa o primeiro Node da LinkedList de ocorrências da 'word' de entrada
                // e pega seu elemento (a linha) para adicionar à Word do nó da ABB.
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

    // --- Métodos de Busca ---
    private Word searchRecursive(NodeABB currentNodeABB, String wordTextToSearch) {
        if (currentNodeABB == null) {
            return null; // Palavra não encontrada
        }

        int comparation = wordTextToSearch.compareTo(currentNodeABB.getElement().getWord());

        if (comparation == 0) {
            return currentNodeABB.getElement(); // Palavra encontrada, retorna o objeto Word
        } else if (comparation < 0) {
            return searchRecursive(currentNodeABB.getLeft(), wordTextToSearch); // Busca na subárvore esquerda
        } else {
            return searchRecursive(currentNodeABB.getRight(), wordTextToSearch); // Busca na subárvore direita
        }
    }

    public Word search(String wordTextToSearch) {
        return searchRecursive(root, wordTextToSearch);
    }

    // --- Métodos de Impressão (incluindo o printIndexToWriter) ---

    // Método recursivo privado para percorrer a ABB e escrever no PrintWriter
    // Este método é usado pela ABB de palavras-chave (keywordsABB no Main)
    // para buscar na mainHashTable e escrever no arquivo de saída do índice remissivo.
    private void printIndexToWriterRecursive(NodeABB currentNodeABB, HashTable mainHashTable, PrintWriter writer) {
        if (currentNodeABB != null) {
            // 1. Percorre a subárvore esquerda (garante a ordem alfabética)
            printIndexToWriterRecursive(currentNodeABB.getLeft(), mainHashTable, writer);

            // 2. Processa o nó atual (que contém uma 'Word' de palavra-chave)
            Word keywordWord = currentNodeABB.getElement(); // Pega a Word da palavra-chave da ABB

            // Busca esta palavra-chave na HashTable principal (que contém as ocorrências do texto)
            Word foundWordInMainText = mainHashTable.search(keywordWord.getWord());

            if (foundWordInMainText != null) {
                // Se a palavra-chave foi encontrada no texto principal, imprime sua representação.
                // Word.toString() já deve estar formatado como "palavra linha1 linha2 linha3".
                writer.println(foundWordInMainText.toString());
            }

            // 3. Percorre a subárvore direita
            printIndexToWriterRecursive(currentNodeABB.getRight(), mainHashTable, writer);
        }
    }

    /**
     * Inicia o processo de impressão do índice remissivo para um PrintWriter.
     * Percorre a árvore (que deve conter as palavras-chave) em ordem alfabética,
     * busca cada palavra-chave na HashTable principal e escreve o resultado formatado.
     *
     * @param mainHashTable A HashTable principal que contém todas as palavras do texto e suas ocorrências.
     * @param writer O PrintWriter para o qual a saída formatada do índice será escrita.
     */
    public void printIndexToWriter(HashTable mainHashTable, PrintWriter writer) {
        // Inicia a recursão a partir da raiz da árvore.
        printIndexToWriterRecursive(this.root, mainHashTable, writer);
    }

    // Outros métodos de impressão para depuração, se existirem
    private void printInRecursiveOrder(NodeABB currentNodeABB) {
        if (currentNodeABB != null) {
            printInRecursiveOrder(currentNodeABB.getLeft());
            System.out.println(currentNodeABB.getElement()); // Imprime a Word (que chamará o toString da Word)
            printInRecursiveOrder(currentNodeABB.getRight());
        }
    }

    public void printInOrder() {
        printInRecursiveOrder(root);
    }
}