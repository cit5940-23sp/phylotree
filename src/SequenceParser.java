import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequenceParser {
    
    /**
     * Parse the file to get the species' name and DNA sequence
     * @param fileName is the input file to parse 
     * (format: first line always the header, the rest is the DNA sequence)
     * @return a Species object where its name and its DNA sequence are stored
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

            String headerTokens[] = header.split("\s+");
            String genusName = headerTokens[1];
            String specificEpithet = headerTokens[2];
            
            ret.setGenusName(genusName);
            ret.setSpecificEpithet(specificEpithet);
            
            // read the rest of the file and store it in sequence
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sequence += line;
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
    public List<Species> parseFolder(String folderPath) {
        File folder = new File(folderPath);
        
        // get all the files in the directory
        File[] files = folder.listFiles();

        List<Species> ret = new ArrayList<Species>();

        for (File file : files) {
            ret.add(parseSequence(file.getPath()));
        }

        return ret;

    }

}
  
  