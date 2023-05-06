
public class Node {
    private String id;
    private String specName;
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    
    public Node(String specName, Node parent, Node leftChild, Node rightChild) {
        this.setSpecName(specName);
        this.setParent(parent);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }
    
    public Node(String specName) {
        this.setSpecName(specName);
        this.setParent(null);
        this.setLeftChild(null);
        this.setRightChild(null);
    }
    
    public Node(Node leftChild, Node rightChild) {
        this.setSpecName(null);
        this.setParent(null);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
