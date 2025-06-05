package br.com.unifor.structures;

public class NodeABB {
    private Word element;
    private NodeABB left;
    private NodeABB right;

    public NodeABB(Word element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public Word getElement() {
        return element;
    }

    public void setElement(Word element) {
        this.element = element;
    }

    public NodeABB getLeft() {
        return left;
    }

    public void setLeft(NodeABB left) {
        this.left = left;
    }

    public NodeABB getRight() {
        return right;
    }

    public void setRight(NodeABB right) {
        this.right = right;
    }
}
