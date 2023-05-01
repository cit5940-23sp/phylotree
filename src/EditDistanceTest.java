import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class EditDistanceTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void editDistFullyDifferentTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(10, ed.editDist(testList.get(0), testList.get(1)));
    }
    
    @Test
    public void editDistSameSeqTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(0, ed.editDist(testList.get(0), testList.get(0)));
    }
    
    @Test
    public void editDistPartiallyDifferentTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(7, ed.editDist(testList.get(0), testList.get(2)));
    }
    
    @Test
    public void editDistMatrixTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        int[][] matrix = ed.editDistMatrix();
        assertEquals(0, matrix[0][0]);
        assertEquals(7, matrix[2][0]);
        assertEquals(matrix[2][0], matrix[0][2]);
    }

}
