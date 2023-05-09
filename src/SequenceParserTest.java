import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class SequenceParserTest {
    String folderPath = "test/complex3";
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void parseSequence() {
        SequenceParser sp = new SequenceParser(500);
        Species actual = sp.parseSequence(folderPath + "/accipiter_gentilis.txt");

        Species expected = new Species();
        expected.setGenusName("Accipiter");
        expected.setSpecificEpithet("gentilis");

        String expectedSequence = SequenceParserTestConstants.sequence1AccipiterGentilis;

        expectedSequence = expectedSequence.replace("\n", "");

        expected.setSequence(expectedSequence);

        assertEquals(expected.getGenusName(), actual.getGenusName());
        assertEquals(expected.getSpecificEpithet(), actual.getSpecificEpithet());
        assertEquals(expected.getSequence(), actual.getSequence());
    }

    @org.junit.jupiter.api.Test
    void parseFolder() {
        SequenceParser sp = new SequenceParser(500);
        List<Species> actualTemp = sp.parseFolder(folderPath);

        Set<Species> actual = new TreeSet<>(new SequenceComparator());
        actual.addAll(actualTemp);


        Species species1 = new Species();
        species1.setGenusName("Accipiter");
        species1.setSpecificEpithet("gentilis");
        String sequence1 = SequenceParserTestConstants.sequence1AccipiterGentilis;
        sequence1 = sequence1.replace("\n", "");
        species1.setSequence(sequence1);

        Species species2 = new Species();
        species2.setGenusName("Ailuropoda");
        species2.setSpecificEpithet("melanoleuca");
        String sequence2 = SequenceParserTestConstants.sequence2AiluropodaMelanoleuca;
        sequence2 = sequence2.replace("\n", "");
        species2.setSequence(sequence2);

        Species species3 = new Species();
        species3.setGenusName("Alligator");
        species3.setSpecificEpithet("mississippiensis");
        String sequence3 = SequenceParserTestConstants.sequence3AlligatorMississippiensis;
        sequence3 = sequence3.replace("\n", "");
        species3.setSequence(sequence3);

        assertEquals(3, actual.size());

        Map<String, Species> map = new HashMap<>();
        map.put("Accipiter", species1);
        map.put("Ailuropoda", species2);
        map.put("Alligator", species3);


        //check if the species set contains the species
        assertTrue(actual.contains(species1));
        assertTrue(actual.contains(species2));
        assertTrue(actual.contains(species3));
    }
}