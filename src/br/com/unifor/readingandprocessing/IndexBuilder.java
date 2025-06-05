package br.com.unifor.readingandprocessing;

import br.com.unifor.structures.HashTable;
import br.com.unifor.structures.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IndexBuilder {
    private HashTable hashTable;

    public IndexBuilder(HashTable hashTable) {
        this.hashTable = hashTable;
    }

    public void processTextFile(String textFilePath) {
        File file = new File(textFilePath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath));
            String line;
            int lineNumber = 1;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\W+");

                for (String w: words) {
                    if (w.isEmpty()) continue;
                    String wordLower = w.toLowerCase();
                    Word keyWord = hashTable.search(wordLower);
                    if (keyWord != null) {
                        keyWord.addOcurrence(lineNumber);
                    }
                }
                lineNumber++;
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }
}
