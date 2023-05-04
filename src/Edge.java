
public class Edge implements Comparable<Edge>{
    
    private Node spec1;
    private Node spec2;
    private int dist;
    
    public Edge(Node spec1, Node spec2, int dist) {
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.dist = dist;
    }
    
    public Node getSpec1() {
        return this.spec1;
    }
    
    public Node getSpec2() {
        return this.spec2;
    }
    
    public int getDist() {
        return this.dist;
    }
    
    public Node setSpec1(Node newNode) {
        return this.spec1 = newNode;
    }
    
    public Node getSpec2(Node newNode) {
        return this.spec2 = newNode;
    }
    
    public int setDist(int newDist) {
        return this.dist = newDist;
    }

    public int compare(Edge e) {
        return -(this.getDist() - e.dist);
    }

    @Override
    public int compareTo(Edge e) {
        return -(this.getDist() - e.dist);
    }

}
