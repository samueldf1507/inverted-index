package dev;
import java.io.*;
import java.util.*;

// Classe para representar uma palavra com suas ocorrências
class Palavra {
    private String palavra;
    private ListaEncadeada ocorrencias;
    
    public Palavra(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.ocorrencias = new ListaEncadeada();
    }
    
    public String getPalavra() {
        return palavra;
    }
    
    public ListaEncadeada getOcorrencias() {
        return ocorrencias;
    }
    
    public void adicionarOcorrencia(int linha) {
        ocorrencias.inserir(linha);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(palavra).append(": ");
        sb.append(ocorrencias.toString());
        return sb.toString();
    }
}

// Classe para implementar Lista Encadeada
class NoLista {
    int dado;
    NoLista proximo;
    
    public NoLista(int dado) {
        this.dado = dado;
        this.proximo = null;
    }
}

class ListaEncadeada {
    private NoLista inicio;
    
    public ListaEncadeada() {
        this.inicio = null;
    }
    
    public void inserir(int valor) {
        NoLista novoNo = new NoLista(valor);
        if (inicio == null) {
            inicio = novoNo;
        } else {
            NoLista atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NoLista atual = inicio;
        while (atual != null) {
            sb.append(atual.dado);
            if (atual.proximo != null) {
                sb.append(", ");
            }
            atual = atual.proximo;
        }
        return sb.toString();
    }
}

// Classe para implementar Árvore Binária de Busca
class NoArvore {
    Palavra palavra;
    NoArvore esquerda;
    NoArvore direita;
    
    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreBinariaBusca {
    private NoArvore raiz;
    
    public ArvoreBinariaBusca() {
        this.raiz = null;
    }
    
    public void inserir(Palavra palavra) {
        raiz = inserirRecursivo(raiz, palavra);
    }
    
    private NoArvore inserirRecursivo(NoArvore no, Palavra palavra) {
        if (no == null) {
            return new NoArvore(palavra);
        }
        
        int comparacao = palavra.getPalavra().compareTo(no.palavra.getPalavra());
        if (comparacao < 0) {
            no.esquerda = inserirRecursivo(no.esquerda, palavra);
        } else if (comparacao > 0) {
            no.direita = inserirRecursivo(no.direita, palavra);
        } else {
            // Palavra já existe, adicionar nova ocorrência
            return no;
        }
        
        return no;
    }
    
    public Palavra buscar(String palavraBusca) {
        return buscarRecursivo(raiz, palavraBusca.toLowerCase());
    }
    
    private Palavra buscarRecursivo(NoArvore no, String palavraBusca) {
        if (no == null) {
            return null;
        }
        
        int comparacao = palavraBusca.compareTo(no.palavra.getPalavra());
        if (comparacao == 0) {
            return no.palavra;
        } else if (comparacao < 0) {
            return buscarRecursivo(no.esquerda, palavraBusca);
        } else {
            return buscarRecursivo(no.direita, palavraBusca);
        }
    }
    
    public void percorrerEmOrdem(List<Palavra> lista) {
        percorrerEmOrdemRecursivo(raiz, lista);
    }
    
    private void percorrerEmOrdemRecursivo(NoArvore no, List<Palavra> lista) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.esquerda, lista);
            lista.add(no.palavra);
            percorrerEmOrdemRecursivo(no.direita, lista);
        }
    }
}

// Classe para implementar Tabela Hash
class TabelaHash {
    private ArvoreBinariaBusca[] tabela;
    private int tamanho;
    
    public TabelaHash() {
        this.tamanho = 26; // 26 letras do alfabeto
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }
    
    private int funcaoHash(String palavra) {
        char primeiraLetra = palavra.toLowerCase().charAt(0);
        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        }
        return 0; // Para caracteres não alfabéticos
    }
    
    public void inserir(Palavra palavra) {
        int indice = funcaoHash(palavra.getPalavra());
        tabela[indice].inserir(palavra);
    }
    
    public Palavra buscar(String palavraBusca) {
        int indice = funcaoHash(palavraBusca);
        return tabela[indice].buscar(palavraBusca);
    }
    
    public List<Palavra> obterTodasPalavrasOrdenadas() {
        List<Palavra> todasPalavras = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            tabela[i].percorrerEmOrdem(todasPalavras);
        }
        return todasPalavras;
    }
}

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