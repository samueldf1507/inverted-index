package br.com.unifor.structures;

public class Word {
    private String word;
    private LinkedList<Integer> occurrences;

    public Word(String word) {
        this.word = word;
        this.occurrences = new LinkedList();
    }

    public void addOcurrence(int line) {
        occurrences.add(line);
    }

    public String getWord() {
        return word;
    }

    public LinkedList getOcurrences() {
        return occurrences;
    }

    @Override
    public String toString() {
        // Este método depende DIRETAMENTE do toString() da LinkedList
        // para imprimir as ocorrências no formato correto.
        // Se a LinkedList.toString() retornar algo como "4 5 6",
        // então este toString retornará "programming 4 5 6".
        return word + " " + occurrences.toString();
    }
}
