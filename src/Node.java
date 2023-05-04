
public class Node {
    
    private Species spec;
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    
    public Node(Species spec, Node parent, Node leftChild, Node rightChild) {
        this.setSpec(spec);
        this.setParent(parent);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }
    
    public Node(Species spec) {
        this.setSpec(spec);
        this.setParent(null);
        this.setLeftChild(null);
        this.setRightChild(null);
    }
    
    public Node(Node leftChild, Node rightChild) {
        this.setSpec(null);
        this.setParent(null);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public Species getSpec() {
        return spec;
    }

    public void setSpec(Species spec) {
        this.spec = spec;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    
    

}
