import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Stream;

class TrieTreeBuilderTest {

    @org.junit.jupiter.api.Test
    void testBuildTreeSimple() {
        TrieTreeBuilder ttb = new TrieTreeBuilder();
        ttb.setUp("test/basic");
        ttb.buildTree();
//        Node mainRoot = ttb.getMainRoot();
//        assertEquals("Test1 test1", mainRoot.getLeftChild().getLeftChild().getSpecName());
    }


    @org.junit.jupiter.api.Test
    void testBuildTreeComplex() {
    }
}