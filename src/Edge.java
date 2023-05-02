
public class Edge {
    
    private Species spec1;
    private Species spec2;
    private int dist;
    
    public Edge(Species spec1, Species spec2, int dist) {
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.dist = dist;
    }
    
    public Species spec1() {
        return this.spec1;
    }
    
    public Species spec2() {
        return this.spec2;
    }
    
    public int dist() {
        return this.dist;
    }

    public int compare(Edge e) {
        return this.dist() - e.dist;
    }

}
