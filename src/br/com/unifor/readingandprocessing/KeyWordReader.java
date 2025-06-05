package br.com.unifor.readingandprocessing;

import br.com.unifor.structures.BinarySearchTree; // Importe sua ABB customizada
import br.com.unifor.structures.Word; // Sua classe Word
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale; // Permitido, não é uma estrutura de dados

public class KeyWordReader {
    // Este construtor e o atributo 'hashTable' não são mais necessários
    // pois a KeyWordReader agora vai RETORNAR uma BinarySearchTree com as palavras-chave.
    // public KeyWordReader(HashTable keyWordTable) {
    //     this.hashTable = keyWordTable;
    // }

    // Adicione um construtor vazio, se necessário, ou remova se for usar apenas métodos estáticos.
    public KeyWordReader() {
        // Nada a inicializar
    }

    /**
     * Lê um arquivo de palavras-chave e as insere em uma BinarySearchTree auxiliar.
     * Esta ABB auxiliar armazena as palavras-chave de forma ordenada e única.
     * As 'Word's criadas aqui para as palavras-chave não terão ocorrências de linha,
     * pois o objetivo é apenas usá-las para buscar no índice principal.
     *
     * @param filepath O caminho para o arquivo de palavras-chave.
     * @return Uma BinarySearchTree populada com as palavras-chave lidas.
     */
    public BinarySearchTree readKeyWordsToABB(String filepath) {
        // Cria uma nova BinarySearchTree para armazenar as palavras-chave
        BinarySearchTree keyWordABB = new BinarySearchTree();
        File file = new File(filepath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Divide a linha por espaços para lidar com múltiplas palavras-chave por linha, se for o caso
                // e normaliza para minúsculas.
                String[] wordsInLine = line.trim().toLowerCase(Locale.ROOT).split("\\s+");
                for (String kw : wordsInLine) {
                    if (!kw.isEmpty()) {
                        // Cria um objeto Word para a palavra-chave.
                        // Neste ponto, a 'Word' não precisa ter ocorrências de linha do texto principal,
                        // pois ela é apenas a chave de busca.
                        Word keywordWord = new Word(kw);
                        // Insere a Word na ABB auxiliar. A ABB (sua implementação)
                        // já deve lidar com a lógica de não adicionar duplicatas exatas
                        // ou de simplesmente retornar o nó existente se a palavra já está lá.
                        keyWordABB.insert(keywordWord);
                    }
                }
            }
            // Não é necessário fechar bufferedReader explicitamente devido ao try-with-resources
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de palavras-chave: " + e.getMessage());
            // Pode-se lançar uma RuntimeException aqui se o programa não puder prosseguir sem as palavras-chave
            // throw new RuntimeException("Falha ao carregar palavras-chave.", e);
        }
        return keyWordABB; // Retorna a ABB preenchida com as palavras-chave
    }
}