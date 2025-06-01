package br.com.unifor.structures;

public class Word {
    private String word;
    private LinkedList ocurrenceList;

    public Word(String text) {
        this.word = text;
        this.ocurrenceList = new LinkedList();
    }

    public void addOcurrence(int position) {
        this.ocurrenceList.addAtAnyPosition(position, this.ocurrenceList.getNumberOfElements());
    }

    public void imprimirOcorrencias() {
        System.out.print("Palavra: \"" + this.word + "\" aparece nas posições: ");
        this.ocurrenceList.printLinkedList();
    }

}
