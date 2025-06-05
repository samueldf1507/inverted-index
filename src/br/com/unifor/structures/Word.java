package br.com.unifor.structures;

public class Word {
    private String word;
    private LinkedList occurrences;

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
}
