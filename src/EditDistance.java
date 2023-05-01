import java.util.List;

public class EditDistance {
    
    List<Species> specList;
    
    public EditDistance(List<Species> inSpecList) {
        this.specList = inSpecList;
    }
    
    /**
     * Create a matrix of edit distances where indices correspond to ID numbers
     * @return a 2D array
     */
    public int[][] editDistMatrix() {
        // Initialize 2D array
        int[][] matrix = new int[specList.size()][specList.size()];
        
        // Iterate through every pair of species
        for (Species spec1 : specList) {
            for (Species spec2 : specList) {
                
                // Find edit distance
                int dist = editDist(spec1, spec2);
                
                // Add both "edges"
                matrix[spec1.getID()][spec2.getID()] = dist;
                matrix[spec2.getID()][spec1.getID()] = dist;
            }
        }
        
        return matrix;
    }
    
    /**
     * Calculate edit distances between two species
     * @param input species whose sequences will be compared
     * @return edit distance
     */
    public int editDist(Species spec1, Species spec2) {
        // Get both sequences
        String seq1 = spec1.getSequence();
        String seq2 = spec2.getSequence();
        
        // Create 2D array for dynamic programming approach 
        int[][] dpArray = new int[seq1.length() + 1][seq2.length() + 1];
        
        // Iterate through every pair of letters between the 2 strings
        for (int i = 0; i <= seq1.length(); i++) {
            for (int j = 0; j <= seq2.length(); j++) {
                
                // Case 1: seq1 is empty
                if (i == 0) {
                    dpArray[i][j] = j;
                }
                
                // Case 2: seq2 is empty 
                else if (j == 0) {
                    dpArray[i][j] = i;
                }
                // Case 3: last char matches and isn't N
                else if (seq1.charAt(i - 1) == seq2.charAt(j - 1) && seq1.charAt(i - 1) != 'N') {
                    dpArray[i][j] = dpArray[i - 1][j - 1];
                }
                // Case 4: last char doesn't match
                else {
                    // Take the min of insert, delete, replace options
                    dpArray[i][j] = 1 + Math.min(dpArray[i][j - 1],
                            Math.min(dpArray[i - 1][j], dpArray[i - 1][j - 1]));
                }
            }
        }
        
        return dpArray[seq1.length()][seq2.length()];
    }
}
