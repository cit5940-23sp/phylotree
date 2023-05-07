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
        SequenceParser sp = new SequenceParser(Integer.MAX_VALUE);
        List<Species> specList = sp.parseFolder("sequences");
        TrieTreeBuilder ttb = new TrieTreeBuilder(specList);
        Node root = ttb.buildTree();
        System.out.println(root);
    }
}