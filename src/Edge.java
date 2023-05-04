
public class Edge implements Comparable<Edge>{
    
    private Node spec1;
    private Node spec2;
    private int dist;
    
    public Edge(Node spec1, Node spec2, int dist) {
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.dist = dist;
    }
    
    public Node spec1() {
        return this.spec1;
    }
    
    public Node spec2() {
        return this.spec2;
    }
    
    public int dist() {
        return this.dist;
    }

    public int compare(Edge e) {
        return -(this.dist() - e.dist);
    }

    @Override
    public int compareTo(Edge e) {
        return -(this.dist() - e.dist);
    }

}
