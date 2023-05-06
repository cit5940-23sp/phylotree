// https://en.wikipedia.org/wiki/File:NewickExample.svg
//
// (A:0.1,B:0.2,(C:0.3,D:0.4):0.5);
// distances and leaf names (popular)
//

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;
import java.util.stream.Stream;

/**
 * This is a class that acts as a wrapper around the Phylogenetic Tree as a `Node`.
 * It will hold both the actual tree (represented as a `Node` object) as well as
 * the graphical representation of the tree (represented as a `Graph` graph stream
 * object.
 */
public class PhyloTree {
    private Node root;
    private Graph graph_stream;
    private int count = 0;
    private final String GRAPH_STREAM_ID = "PhyloTree";

    /**
     * PhyloTree from Node/Edge tree
     * @param node
     */
    PhyloTree(Node node) {
        this.root = node;
        this.graph_stream = new SingleGraph(GRAPH_STREAM_ID);
    }

    /**
     * Find top 5 nearest neighbours of given species (query by common name)
     *
     * @param commonName
     * @param speciesList
     * @param editDistance
     * @return
     */
    public List<String> nearestBySpeciesName(String commonName, List<Species> speciesList, int[][] editDistance) {
        return null;
    }

    /**
     * Find top 5 nearest sequences of given species (query by the DNA sequence)
     *
     * @param sequence
     * @param speciesList
     */
    public List<String> nearestBySequence(String sequence, List<Species> speciesList) {
        return null;
    }

    private boolean isLeaf(Node node) {
        return node.getLeftChild() == null && node.getRightChild() == null;
    }

    private void buildGraphStreamInOrder(Node node)
    {
        if (node == null)
            return;

        String id;

        // left child
        buildGraphStreamInOrder(node.getLeftChild());

        // give node a unique id
        if (isLeaf(node)) {
            // if leaf, use species name
            id = node.getSpecName();
            org.graphstream.graph.Node n = this.graph_stream.addNode(node.getSpecName());
            // visual attributes for leaf nodes
            n.setAttribute("ui.style", "text-mode: normal;" +
                    "text-background-mode: plain;" +
                    "text-alignment: under;" +
                    "text-size: 40;");
            n.setAttribute("ui.label", id);
            node.setId(id);
        } else {
            // ow use unique count
            id = String.valueOf(count++);
            this.graph_stream.addNode(id);
            node.setId(id);
        }

        // right child
        buildGraphStreamInOrder(node.getRightChild());

        if (!isLeaf(node)) {
            // add edges
            this.graph_stream.addEdge(id + ":" +  node.getLeftChild().getId(),
                    id, node.getLeftChild().getId());
            this.graph_stream.addEdge(id + ":" +  node.getRightChild().getId(),
                    id, node.getRightChild().getId());
        }
    }

    public void buildGraphStream() {
        graph_stream.clear();
        this.count = 0;
        buildGraphStreamInOrder(this.root);
    }

    public void setRoot(Node node) {
        this.root = node;
    }

    public Node getRoot() {
        return this.root;
    }

    public Graph getGraphStream() {
        return this.graph_stream;
    }
}
