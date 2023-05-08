import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Stream;

class PhyloTreeTest {

    @org.junit.jupiter.api.Test
    void testRunGUI() {
        PhyloGUI gui = new PhyloGUI("test/basic");
        gui.run();
        assertNotNull(gui);
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

    @org.junit.jupiter.api.Test
    void testNearestBySpeciesNameSimple() {
        // create a graph with 3 files
        PhyloTree pt = new PhyloTree(null);
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("test/basic");
        EditDistance ed = new EditDistance(testList);
        int[][] editDistance = ed.editDistMatrix();
        List<String> nearest = pt.nearestBySpeciesName("Test1 test1", testList,editDistance);
        assertEquals(nearest.size(),3); // The top 3 closest species
    }

    @org.junit.jupiter.api.Test
    void testNearestBySequenceSimple() {
        // create a graph with 3 files
        PhyloTree pt = new PhyloTree(null);
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("test/basic");
        List<String> nearest = pt.nearestBySequence("AAAAAAAAAA",testList);
        assertEquals(nearest.get(0).toString(),"Test1 test1");
    }
}