package src;

import java.util.Comparator;

public class SequenceComparator implements Comparator<Species> {

    @Override
    public int compare(Species s1, Species s2) {

        //compare the genus name
        int genusComparison = s1.getGenusName().compareTo(s2.getGenusName());
        if (genusComparison != 0) {
            return genusComparison;
        }

        //if the genus names are the same, compare the specific epithet
        int specificComparison = s1.getSpecificEpithet().compareTo(s2.getSpecificEpithet());
        if (specificComparison != 0) {
            return specificComparison;
        }

        //if the specific epithets are the same, compare the sequences (they will ideally never be the same)
        return s1.getSequence().compareTo(s2.getSequence());
    }
}