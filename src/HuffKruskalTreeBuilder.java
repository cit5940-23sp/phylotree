import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffKruskalTreeBuilder implements ITreeBuilder {
    
    private List<Species> specList;
    private int[][] matrix;
    
    public void setUp(String folderPath) {
        SequenceParser sp = new SequenceParser();
        
        specList = sp.parseFolder(folderPath);
        
        EditDistance ed = new EditDistance(specList);
        
        this.matrix = ed.editDistMatrix();
    }

    @Override
    public Graph buildTree() {
        // Use Kruskal based approach to sort edges and always pick lowest
        // Use Huffman based approach to take two species and put them under on internal node
        
        // Take smallest unseen edge and add the two species it connect under a parent internal node
        // Update all edge weights for those two species to be the average distance for both?
        // Or just always use min?
        
        Queue<Edge> frontierQ = getEdgeQueue();
        
        while (!frontierQ.isEmpty()) {
            Edge nextMin = frontierQ.poll();
        }
        
        return null;
    }
    
    // Helper
    private Queue<Edge> getEdgeQueue() {
        Queue<Edge> pq = new PriorityQueue<Edge>();
        
        for (int i = 0; i < specList.size(); i++) {
            for (int j = i; j < specList.size(); j++) {
                Edge newEdge = new Edge (specList.get(i), specList.get(j), matrix[i][j]);
                pq.add(newEdge);
            }
        }
        
        return pq;
    }
}
