import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Stream;

class PhyloTreeTest {

    @org.junit.jupiter.api.Test
    void testBuildGraphStreamComplex() {

    }


    @org.junit.jupiter.api.Test
    void testBuildGraphStreamSimple() {
        // create a simple node graph
        Node root = new Node(new Node(null), new Node("Leaf D"));
        root.getLeftChild().setLeftChild(new Node("Leaf A"));
        root.getLeftChild().setRightChild(new Node(null));
        root.getLeftChild().getRightChild().setLeftChild(new Node("Leaf B"));
        root.getLeftChild().getRightChild().setRightChild(new Node("Leaf C"));
        PhyloTree pt = new PhyloTree(root);
        pt.buildGraphStream();
    }

}