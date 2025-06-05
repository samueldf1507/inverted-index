package br.com.unifor.readingandprocessing;

import br.com.unifor.structures.HashTable;
import br.com.unifor.structures.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KeyWordReader {
    private HashTable hashTable;

    public KeyWordReader(HashTable keyWordTable) {
        this.hashTable = keyWordTable;
    }

    public void readKeyWords(String filepath) {
        File file = new File(filepath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (!line.isEmpty()) {
                    Word word = new Word(line);
                    hashTable.insert(word);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de palavras-chave: " + e.getMessage());
        }

    }
}
