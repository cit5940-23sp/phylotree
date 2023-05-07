import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HuffKruskalTreeBuilderTest {

    @Test
    void testBuildTreeBasic() {
        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder("test/basic");
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        assertEquals("Test3 test3", mainRoot.getRightChild().getRightChild().getSpecName());
    }
    
    @Test
    void testBuildTreeComplex() {
        // parse folder
        SequenceParser sp = new SequenceParser(Integer.MAX_VALUE);
        List<Species> specList = sp.parseFolder("test/complex");

        // generate edit distance matrix
        EditDistance ed = new EditDistance(specList);
        int[][] matrix = ed.editDistMatrix();

        HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder(specList, matrix);
        hktb.buildTree();
        Node mainRoot = hktb.getMainRoot();
        assertEquals("American Alligator", mainRoot.getLeftChild().getSpecName());
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
