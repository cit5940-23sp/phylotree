import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffKruskalTreeBuilderTest {

    static final String TEST_FOLDER = "test/basic";

    @Test
    void testBuildTree() {
        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder();
        hktb.setUp(TEST_FOLDER);
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        System.out.println(mainRoot.getLeftChild().getLeftChild().getSpec().toString());
    }

}
