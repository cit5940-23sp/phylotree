// https://en.wikipedia.org/wiki/File:NewickExample.svg
//
// (A:0.1,B:0.2,(C:0.3,D:0.4):0.5);
// distances and leaf names (popular)
//

import org.graphstream.graph.Graph;

/**
 * This is a class that acts as a wrapper around the Phylogenetic Tree as a `Node`.
 * It is useful for converting our tree to Newick format, and can also build a
 * tree from a newick formatted string.
 */
public class PhyloTree {
    Node root;
    Graph graphstream;

    /**
     * PhyloTree from Newick String, creates a Node/Edge Tree
     * @param newick string to parse to a phyloTree
     */
    PhyloTree(String newick) {
    }

    /**
     * PhyloTree from Node/Edge tree
     * @param node
     */
    PhyloTree(Node node) {
    }

    public Node getRootNode() {
        return null;
    }

    public String toNewickString() {
        return null;
    }

    public void buildGraphStream() {
    }
}
