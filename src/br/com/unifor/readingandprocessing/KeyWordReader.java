package br.com.unifor.readingandprocessing;

import br.com.unifor.structures.BinarySearchTree; // Importe sua ABB customizada
import br.com.unifor.structures.Word; // Sua classe Word
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale; // Permitido, não é uma estrutura de dados

public class KeyWordReader {
    public BinarySearchTree readKeyWordsToABB(String filepath) {
        BinarySearchTree keyWordABB = new BinarySearchTree();
        File file = new File(filepath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsInLine = line.trim().toLowerCase(Locale.ROOT).split("\\s+");
                for (String kw : wordsInLine) {
                    if (!kw.isEmpty()) {
                        Word keywordWord = new Word(kw);
                        keyWordABB.insert(keywordWord);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de palavras-chave: " + e.getMessage());
        }
        return keyWordABB;
    }
}