import java.util.ArrayList;
import java.util.List;

public class PaperTreeBuilder implements ITreeBuilder {
    private List<Species> specList;

    private List<Integer> notVisited;

    private int[][] matrix;
    private Node mainRoot;

    /**
     * Sets up necessary components for tree conversion
     * @param folderPath
     * @return
     */
    public void setUp(String folderPath){
        // parse folder
        SequenceParser sp = new SequenceParser();
        // generate edit distance matrix
        this.specList = sp.parseFolder(folderPath);

        EditDistance ed = new EditDistance(specList);
        this.matrix = ed.editDistMatrix();
    }

    /**
     * Converts data parsed from DNA sequences to a phylogenetic tree.
     * @return root node of tree
     */
   public Node buildTree(){
       //nodes not yet completed
       this.notVisited = new ArrayList<>();

       //add the IDs of the nodes not yet visited
       for (int i = 0; i < specList.size();i++) {
           notVisited.add(specList.get(i).getID());
       }

       return buildPaperTree(notVisited.size());
    }

    public Node buildPaperTree(int n){
       if (notVisited.size() == 2) {

           Node spec1 = new Node(notVisited.get(0).toString());
           Node spec2 = new Node(notVisited.get(1).toString());

           Node parent = new Node(spec1,spec2);
           //add the edge

           Edge edge = new Edge(spec1,spec2,0);

           return parent;
       }

        int[][] dStar = new int[n][n];

        int minDStar = Integer.MAX_VALUE;
        int minI = -1, minJ = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (i == j) continue; // Skip diagonal elements
                if (dStar[i][j] < minDStar) {
                    minDStar = dStar[i][j];
                    minI = i;
                    minJ = j;
                }
            }
        }

        //get the total distance matrix
        int[] totalDistance = new int[n];
        int sum = 0;
        for (int i = 0; i < n;i++){
            for (int j = 0;j < n;j++) {
                sum += matrix[i][j];
            }
            totalDistance[i] = sum;
        }

        //calculate delta
        int delta = (totalDistance[minI] - totalDistance[minJ])/(n-2);

        //calculate the limb lengths
        int limbLengthI = (matrix[minI][minJ] + delta) / 2;
        int limbLengthJ = (matrix[minI][minJ] - delta) / 2;

        //add a new row/column m to D so that Dk,m = Dm,k = 12 (Dk,i + Dk,j   Di,j)
        //for any k
        //remove rows i and j from D
        //remove columns i and j from D
        int[][] newD = new int[n+1][n+1];
        int m = n; // The index of the new row and column
        for (int k = 0; k < n; k++) {
            if (k == minI || k == minJ) continue; // Skip rows and columns i and j
            newD[k][m] = newD[m][k] = (matrix[k][minI] + matrix[k][minJ] - matrix[minI][minJ]) / 2;
        }
        for (int k = 0; k < n; k++) {
            if (k == minI || k == minJ) continue; // Skip rows and columns i and j
            for (int l = 0; l < n; l++) {
                if (l == minI || l == minJ) continue; // Skip rows and columns i and j
                newD[k][l] = matrix[k][l];
            }
        }
        this.matrix = newD; // Update D with the new matrix


        //recurse
        return buildPaperTree(n - 1);
    }
}
