package br.com.unifor.structures;

public class Word {
    private String word;
    private LinkedList ocurrenceList; // Garanta que esta é a sua LinkedList

    public Word(String text) {
        this.word = text;
        this.ocurrenceList = new LinkedList();
    }

    public void addOcurrence(int position) {
        // Melhor usar addLast, que é o que addAtAnyPosition(pos, size) faz
        this.ocurrenceList.addLast(position);
        // Ou, se preferir manter o original:
        // this.ocurrenceList.addAtAnyPosition(position, this.ocurrenceList.getNumberOfElements());
    }

    // NOVO: Métodos getters
    public String getWord() {
        return this.word;
    }

    public LinkedList getOcurrenceList() {
        return this.ocurrenceList;
    }

    // Ajuste para usar o toString da LinkedList
    @Override
    public String toString() {
        // Retorna "palavra linha1 linha2 linha3"
        return this.word + " " + this.ocurrenceList.toString();
    }

    // O método imprimirOcorrencias() ainda é útil para depuração, mas o toString()
    // será usado para a saída formatada do projeto.
    public void imprimirOcorrencias() {
        System.out.print("Palavra: \"" + this.word + "\" aparece nas posições: ");
        this.ocurrenceList.printLinkedList();
    }
}