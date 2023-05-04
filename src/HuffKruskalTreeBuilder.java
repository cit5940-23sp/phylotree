import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffKruskalTreeBuilder implements ITreeBuilder {
    
    private List<Species> specList;
    private int[][] matrix;
    private Node mainRoot;
    
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
            Node root1 = findRoot(nextMin.getSpec1());
            Node root2 = findRoot(nextMin.getSpec2());
            if (root1 != root2) {
                mainRoot = new Node(root1, root2);
                root1.setParent(mainRoot);
                root2.setParent(mainRoot);
            }
        }
        
        return null;
    }
    
    // Helper
    private Queue<Edge> getEdgeQueue() {
        Queue<Edge> pq = new PriorityQueue<Edge>();
        List<Node> nodeList = getNodeList();
        
        for (int i = 0; i < specList.size(); i++) {
            for (int j = i; j < specList.size(); j++) {
                Edge newEdge = new Edge (nodeList.get(i), nodeList.get(j), matrix[i][j]);
                pq.add(newEdge);
            }
        }
        
        return pq;
    }
    
    // Helper
    private List<Node> getNodeList() {
        ArrayList<Node> nodeList = new ArrayList<Node>();
        
        for (int i = 0; i < specList.size(); i++) {
            Node newNode = new Node(specList.get(i));
            nodeList.add(newNode);
        }
        
        return nodeList;
    }
    
    // Helper
    private Node findRoot(Node node) {
        if (node.getParent() == null) {
            return node;
        }
        return findRoot(node.getParent());
    }
    
    // For testing
    public Node getMainRoot() {
        return this.mainRoot;
    }
}