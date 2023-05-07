import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NameMapper {
    
    private String fileName;
    private Map<String, String> map;
    
    public NameMapper() {
        this.fileName = "names.txt";
        this.map = new HashMap<String, String>();
    }
    
    public NameMapper(String fileName) {
        this.fileName = fileName;
        this.map = new HashMap<String, String>();
    }
    
    public Map<String, String> createMap() {
        
        BufferedReader r = null;
        
        try {
            r = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String entry = null;
        
        try {
            entry = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while (!(entry == null)) {
            String[] names = entry.split(",");
            if (names.length == 2) {
                String scientificName = names[0].trim();
                String commonName = names[1].trim();
                map.put(scientificName, commonName);
            }
        }
        
        return map;
    }
    
    public String getName(String scientificName) {
        // If the entry exists, return the common name
        if (map.containsKey(scientificName)) {
            return map.get(scientificName);
        }
        // Otherwise, just return the scientific name
        return scientificName;
    }

}
