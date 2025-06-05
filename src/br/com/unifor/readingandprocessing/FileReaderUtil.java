package br.com.unifor.readingandprocessing;

import br.com.unifor.structures.HashTable;
import br.com.unifor.structures.Word;

import java.io.*;
import java.util.Locale;

public class FileReaderUtil {
    private HashTable hashTable;

    public FileReaderUtil(HashTable table) {
        this.hashTable = table;
    }

    public void readFile(String filepath) {
        File file = new File(filepath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String w: words) {
                    if (!w.isEmpty()) {
                        Word word = new Word(w.toLowerCase(Locale.ROOT));
                        word.addOcurrence(lineNumber);
                        hashTable.insert(word);
                    }
                    
                }
                lineNumber++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de leitura no arquivo: "+ filepath + ":" + e.getMessage());
        }
    }
}
