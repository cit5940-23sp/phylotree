import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class EditDistanceTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void editDistanceFullyDifferentTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(10, ed.editDist(testList.get(0), testList.get(1)));
    }
    
    @Test
    public void editDistanceSameSeqTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(0, ed.editDist(testList.get(0), testList.get(0)));
    }
    
    @Test
    public void editDistancePartiallyDifferentTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        assertEquals(7, ed.editDist(testList.get(0), testList.get(2)));
    }

}
