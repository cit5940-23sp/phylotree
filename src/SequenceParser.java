import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        ret.setSequence(sequence);

        return ret;
    }

}
  
  