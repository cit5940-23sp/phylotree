import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class EditDistanceTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void editDistanceTest() {
        SequenceParser sp = new SequenceParser();
        List<Species> testList = (List<Species>) sp.parseFolder("basicTestSequences");
        EditDistance ed = new EditDistance(testList);
        System.out.println(ed.editDist(testList.get(1), testList.get(1)));
    }

}
