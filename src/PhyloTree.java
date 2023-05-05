// https://en.wikipedia.org/wiki/File:NewickExample.svg
//
// (A:0.1,B:0.2,(C:0.3,D:0.4):0.5);
// distances and leaf names (popular)
//

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;

/**
 * This is a class that acts as a wrapper around the Phylogenetic Tree as a `Node`.
 * It will hold both the actual tree (represented as a `Node` object) as well as
 * the graphical representation of the tree (represented as a `Graph` graph stream
 * object.
 */
public class PhyloTree {
    Node root;
    Graph graphstream;
    final String GRAPH_STREAM_ID = "PhyloTree";

    /**
     * PhyloTree from Node/Edge tree
     * @param node
     */
    PhyloTree(Node node) {
        this.root = node;
        this.graphstream = new SingleGraph(GRAPH_STREAM_ID);
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

    public void setRootNode(Node node) {
        this.root = node;
    }

    public Node getRootNode() {
        return this.root;
    }

    public void buildGraphStream() {
        graphstream.clear();
        // create graph stream from this.root
    }
}
