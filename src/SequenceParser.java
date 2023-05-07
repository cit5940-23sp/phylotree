import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class SequenceParser {
    private final static int DEFAULT_LIMIT = 350;
    private final int sizeLimit;

    SequenceParser() {
        this.sizeLimit = DEFAULT_LIMIT;
    }

    SequenceParser(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    /**
     * Parse the file to get the species' name and DNA sequence
     * @param fileName is the input file to parse 
     * (format: first line always the header, the rest is the DNA sequence)
     * @return a Species object where its name and its DNA sequence are stored,
     * or null if the input file is too large (DNA sequence > 350 lines * 60 characters)
     */
    public Species parseSequence(String fileName) {
        Species ret = new Species();
        String header = "";
        String sequence = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read the first line and store it in header
            header = bufferedReader.readLine();

            //split the header on commas
            String headerTokens[] = header.split("\\s+");
            String genusName = headerTokens[1].trim();

            String specificEpithet = headerTokens[2].trim();
            
            ret.setGenusName(genusName);
            ret.setSpecificEpithet(specificEpithet);

            // create a var to keep track of how many lines have been read
            int numberOfLines = 0;

            // read the rest of the file and store it in sequence
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numberOfLines++;
                sequence += line;
            }

            // we don't want file that contains > 350 lines of ACTG (will blow the heap)
            if (numberOfLines > sizeLimit) {
                ret = null;
                return ret;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sequence = sequence.trim();
        ret.setSequence(sequence);

        return ret;
    }


    /**
     * Parse all files in the given folder and return a list of Species objects
     * @param folderPath is the path of the folder that stores the files to parse
     * @return ret, a list of Species objects
     */
    public List<Species>  parseFolder(String folderPath) {

        File folder = new File(folderPath);

        // get all the files in the directory
        File[] files = folder.listFiles();

        List<Species> ret = new ArrayList<>();

        int id = 0;

        int numberOfFilesIncluded = 0;
        for (File file : files) {
            Species s = parseSequence(file.getPath());

            // if return value from parseSequence is null
            // that means the corresponding file is too big to be included
            if (s == null) {
                continue;
            }

            System.out.println("file included: " + file.getName());
            numberOfFilesIncluded++;

            s.setID(id);
            ret.add(s);
            id++;
        }

        System.out.println("numberOfFilesIncluded: " + numberOfFilesIncluded);

        return ret;
    }
}
  
  