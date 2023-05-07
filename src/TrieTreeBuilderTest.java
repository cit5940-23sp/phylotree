import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Stream;

class TrieTreeBuilderTest {

    @org.junit.jupiter.api.Test
    void testBuildTreeSimple() {
        TrieTreeBuilder ttb = new TrieTreeBuilder();
        ttb.setUp("test/basic");
        Node root = ttb.buildTree();
        System.out.println(root);
    }


    @org.junit.jupiter.api.Test
    void testBuildTreeComplex() {
        TrieTreeBuilder ttb = new TrieTreeBuilder();
        ttb.setUp("test/complex");
        Node root = ttb.buildTree();
    }
}