import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffKruskalTreeBuilderTest {

    @Test
    void testBuildTreeBasic() {
        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder("test/basic");
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        assertEquals("Test1 test1", mainRoot.getLeftChild().getLeftChild().getSpecName());
    }
    
    @Test
    void testBuildTreeComplex() {
        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder("test/complex");
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        assertEquals("Accipiter gentilis", mainRoot.getLeftChild().getSpecName());
    }
    
    @Test
    void testPrintOut() {
        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder("test/complex2");
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        String str = "";
        hktb.printOut(mainRoot, str);
    }

}
