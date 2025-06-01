package dev;
import java.io.*;
import java.util.*;

// Classe para representar uma palavra com suas ocorrências

// Classe para implementar Lista Encadeada




// Classe para implementar Árvore Binária de Busca




// Classe para implementar Tabela Hash


// Classe principal do projeto
public class IndiceRemissivo {
    private TabelaHash tabelaHash;
    
    public IndiceRemissivo() {
        this.tabelaHash = new TabelaHash();
    }
    
    public void processarTexto(String nomeArquivoTexto) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivoTexto))) {
            String linha;
            int numeroLinha = 1;
            
            while ((linha = br.readLine()) != null) {
                processarLinha(linha, numeroLinha);
                numeroLinha++;
            }
            
            System.out.println("Texto processado com sucesso!");
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }
    
    private void processarLinha(String linha, int numeroLinha) {
        // Remove pontuação e divide a linha em palavras
        String[] palavras = linha.toLowerCase()
                                .replaceAll("[^a-zA-Z\\s-]", "")
                                .split("\\s+");
        
        for (String palavraStr : palavras) {
            if (!palavraStr.trim().isEmpty()) {
                palavraStr = palavraStr.trim();
                
                // Busca se a palavra já existe
                Palavra palavraExistente = tabelaHash.buscar(palavraStr);
                
                if (palavraExistente != null) {
                    // Palavra já existe, adiciona nova ocorrência
                    palavraExistente.adicionarOcorrencia(numeroLinha);
                } else {
                    // Palavra não existe, cria nova palavra
                    Palavra novaPalavra = new Palavra(palavraStr);
                    novaPalavra.adicionarOcorrencia(numeroLinha);
                    tabelaHash.inserir(novaPalavra);
                }
            }
        }
    }
    
    public void gerarIndiceRemissivo(String nomeArquivoPalavrasChave, String nomeArquivoSaida) {
        try {
            Set<String> palavrasChave = lerPalavrasChave(nomeArquivoPalavrasChave);
            List<Palavra> indiceRemissivo = new ArrayList<>();
            
            // Busca cada palavra-chave na estrutura
            for (String palavraChave : palavrasChave) {
                Palavra palavra = tabelaHash.buscar(palavraChave.toLowerCase());
                if (palavra != null) {
                    indiceRemissivo.add(palavra);
                }
            }
            
            // Ordena alfabeticamente
            indiceRemissivo.sort((p1, p2) -> p1.getPalavra().compareTo(p2.getPalavra()));
            
            // Escreve o resultado no arquivo de saída
            escreverIndiceRemissivo(indiceRemissivo, nomeArquivoSaida);
            
            System.out.println("Índice remissivo gerado com sucesso em: " + nomeArquivoSaida);
            
        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }
    
    private Set<String> lerPalavrasChave(String nomeArquivo) throws IOException {
        Set<String> palavrasChave = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.toLowerCase()
                                        .replaceAll("[^a-zA-Z\\s-]", "")
                                        .split("\\s+");
                for (String palavra : palavras) {
                    if (!palavra.trim().isEmpty()) {
                        palavrasChave.add(palavra.trim());
                    }
                }
            }
        }
        
        return palavrasChave;
    }
    
    private void escreverIndiceRemissivo(List<Palavra> indice, String nomeArquivo) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo))) {
            pw.println("ÍNDICE REMISSIVO");
            pw.println("================");
            pw.println();
            
            for (Palavra palavra : indice) {
                pw.println(palavra.toString());
            }
        }
    }
    
    // Método para exibir estatísticas (opcional)
    public void exibirEstatisticas() {
        List<Palavra> todasPalavras = tabelaHash.obterTodasPalavrasOrdenadas();
        System.out.println("Total de palavras únicas processadas: " + todasPalavras.size());
    }
    
    public static void main(String[] args) {
        IndiceRemissivo indiceRemissivo = new IndiceRemissivo();
        
        try {
            // Nomes dos arquivos (você pode modificar aqui)
            String arquivoTexto = "texto.txt";
            String arquivoPalavrasChave = "palavras_chave.txt";
            String arquivoSaida = "saida.txt";
            
            System.out.println("Iniciando processamento do índice remissivo...");
            System.out.println("Diretório atual: " + System.getProperty("user.dir"));
            System.out.println("Procurando pelos arquivos:");
            System.out.println("- " + arquivoTexto);
            System.out.println("- " + arquivoPalavrasChave);
            System.out.println();
            
            // Verifica se os arquivos existem
            File textoFile = new File(arquivoTexto);
            File palavrasFile = new File(arquivoPalavrasChave);
            
            if (!textoFile.exists()) {
                System.err.println("ERRO: Arquivo '" + arquivoTexto + "' não encontrado!");
                System.err.println("Certifique-se de que o arquivo está na pasta: " + System.getProperty("user.dir"));
                return;
            }
            
            if (!palavrasFile.exists()) {
                System.err.println("ERRO: Arquivo '" + arquivoPalavrasChave + "' não encontrado!");
                System.err.println("Certifique-se de que o arquivo está na pasta: " + System.getProperty("user.dir"));
                return;
            }
            
            // Processa o texto
            indiceRemissivo.processarTexto(arquivoTexto);
            
            // Gera o índice remissivo
            indiceRemissivo.gerarIndiceRemissivo(arquivoPalavrasChave, arquivoSaida);
            
            // Exibe estatísticas
            indiceRemissivo.exibirEstatisticas();
            
            System.out.println("Processamento concluído!");
            
        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}