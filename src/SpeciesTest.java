import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SpeciesTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @Test
    void equalsTest() {
        Species spec1 = new Species();
        Species spec2 = new Species();
        spec1.setGenusName("Test");
        spec1.setSpecificEpithet("test");
        spec1.setSequence("AAA");
        spec2.setGenusName("Test");
        spec2.setSpecificEpithet("test");
        spec2.setSequence("AAA");
        assertEquals(spec1, spec2);
    }

}
