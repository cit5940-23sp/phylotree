import java.util.List;
import java.util.ArrayList;

public class EditDistance {
    
    List<Species> specList;
    
    public EditDistance(List<Species> inSpecList) {
        this.specList = inSpecList;
    }
    
    public ArrayList<ArrayList<Integer>> editDistMatrix() {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        
        for (Species spec1 : specList) {
            for (Species spec2 : specList) {
                int dist = editDist(spec1, spec2);
                //matrix.get(spec1.getId()).add(spec2.id, dist);
                //matrix.get(spec2.getId()).add(spec1.id, dist);
            }
        }
        
        return matrix;
    }
    
    public int editDist(Species spec1, Species spec2) {
        
        String seq1 = spec1.getSequence();
        String seq2 = spec2.getSequence();
        
        int[][] dpArray = new int[seq1.length() + 1][seq2.length() + 1];
        
        for (int i = 0; i <= seq1.length(); i++) {
            for (int j = 0; j <= seq2.length(); j++) {
                
                // seq1 is empty
                if (i == 0) {
                    dpArray[i][j] = j;
                }
                // seq2 is empty 
                else if (j == 0) {
                    dpArray[i][j] = i;
                }
                // last char matches and isn't N
                else if (seq1.charAt(i - 1) == seq2.charAt(j - 1) && seq1.charAt(i) != 'N') {
                    dpArray[i][j] = dpArray[i - 1][j - 1];
                }
                // last char doesn't match
                else {
                    dpArray[i][j] = 1 + Math.min(dpArray[i][j - 1],
                            Math.min(dpArray[i - 1][j], dpArray[i - 1][j - 1]));
                }
            }
        }
        
        
        return dpArray[seq1.length()][seq2.length()];
    }
}
