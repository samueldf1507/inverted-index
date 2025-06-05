package br.com.unifor;

import br.com.unifor.exceptions.WordOutOfRange;
import br.com.unifor.structures.HashTable;
import br.com.unifor.structures.Word;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileReaderUtil {
    private HashTable hashTable;

    public FileReaderUtil(HashTable hashTable) {
        this.hashTable = hashTable;
    }

    public void readFile(String filepath) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        Word word = new Word(line);
                        hashTable.insert(word);
                    } catch (WordOutOfRange e) {
                        System.err.println("Palavra fora do intervalo: " + e.getMessage());
                    }
                }
            }

        } catch (IIOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

