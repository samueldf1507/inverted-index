
import br.com.unifor.readingandprocessing.FileReaderUtil;
import br.com.unifor.readingandprocessing.KeyWordReader;
import br.com.unifor.structures.BinarySearchTree; // Sua ABB customizada
import br.com.unifor.structures.HashTable;       // Sua HashTable customizada
import br.com.unifor.structures.Word;            // Sua classe Word customizada

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
// Não há importações de java.util.List, ArrayList, Set, HashSet etc.

public class Main {
    public static void main(String[] args) {
        // Nomes dos arquivos de entrada e saída.
        // Crie os arquivos 'texto.txt' e 'palavras_chave.txt'
        // no diretório raiz do seu projeto (onde está a pasta 'src' ou 'bin').
        String textFilePath = "entrada.txt";
        String keywordsFilePath = "palavras_chave.txt";
        String outputFilePath = "saida.txt";

        System.out.println("--- Início do Projeto Índice Remissivo ---");
        System.out.println("Verificando arquivos de entrada...");

        // Verificação da existência dos arquivos de entrada
        File textFile = new File(textFilePath);
        File keywordsFile = new File(keywordsFilePath);

        if (!textFile.exists()) {
            System.err.println("ERRO: Arquivo de texto '" + textFilePath + "' não encontrado!");
            System.err.println("Certifique-se de que ele está no diretório: " + new File("").getAbsolutePath());
            return; // Encerra o programa
        }
        if (!keywordsFile.exists()) {
            System.err.println("ERRO: Arquivo de palavras-chave '" + keywordsFilePath + "' não encontrado!");
            System.err.println("Certifique-se de que ele está no diretório: " + new File("").getAbsolutePath());
            return; // Encerra o programa
        }

        try {
            // 1. Instanciar a HashTable principal que armazenará todas as palavras do texto
            HashTable mainHashTable = new HashTable();

            // 2. Popular a HashTable com as palavras e ocorrências do texto principal.
            // FileReaderUtil.readFile já cuida de criar Words e inserir na HashTable,
            // adicionando ocorrências se a palavra já existe (graças à correção na ABB).
            FileReaderUtil textProcessor = new FileReaderUtil(mainHashTable);
            System.out.println("\nPasso 1/3: Processando arquivo de texto: '" + textFilePath + "'...");
            textProcessor.readFile(textFilePath);
            System.out.println("Passo 1/3: Texto processado com sucesso!");

            // 3. Ler as palavras-chave e armazená-las em uma BinarySearchTree auxiliar.
            // KeyWordReader.readKeyWordsToABB retorna uma ABB populada com as palavras-chave.
            // As Words inseridas aqui terão apenas o texto da palavra-chave, sem ocorrências de linha.
            KeyWordReader keyWordReader = new KeyWordReader();
            System.out.println("\nPasso 2/3: Lendo palavras-chave de: '" + keywordsFilePath + "'...");
            BinarySearchTree keywordsABB = keyWordReader.readKeyWordsToABB(keywordsFilePath);
            System.out.println("Passo 2/3: Palavras-chave lidas e organizadas em uma ABB auxiliar.");

            // 4. Gerar o Índice Remissivo.
            // Este passo envolve percorrer a ABB de palavras-chave em ordem alfabética,
            // buscar cada palavra-chave na mainHashTable (que tem as ocorrências do texto),
            // e escrever a saída diretamente no arquivo, sem usar coleções intermediárias do java.util.
            System.out.println("\nPasso 3/3: Gerando o índice remissivo em: '" + outputFilePath + "'...");
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
                writer.println("ÍNDICE REMISSIVO");
                writer.println("================");
                writer.println(); // Linha em branco

                // Chama o método na ABB das palavras-chave para percorrer em ordem,
                // buscar na mainHashTable e escrever o resultado.
                keywordsABB.printIndexToWriter(mainHashTable, writer);

                System.out.println("Passo 3/3: Índice remissivo gerado com sucesso!");
            }

        } catch (FileNotFoundException e) {
            System.err.println("\nERRO: O arquivo não foi encontrado. Por favor, verifique se o caminho ou nome do arquivo está correto. Detalhes: " + e.getMessage());
            e.printStackTrace(); // Para depuração, mostra o stack trace completo.
        } catch (IOException e) {
            System.err.println("\nERRO de leitura/escrita no arquivo. Detalhes: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { // Captura quaisquer outras exceções inesperadas
            System.err.println("\nERRO INESPERADO durante a execução do programa. Detalhes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n--- Fim do Processamento ---");
        }
    }
}