import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TrieTreeBuilder implements ITreeBuilder {

    private List<Species> specList;
    private Node mainRoot;
    private TrieNode root;

    public TrieTreeBuilder() {}

    /**
     * If species list has already been generated, construct TrieTreeBuilder
     * without having to call setUp.
     * @param specList
     */
    public TrieTreeBuilder(List<Species> specList) {
        this.specList = specList;
    }

    /**
     * Sets up necessary components for tree conversion.
     * @param folderPath
     */
    @Override
    public void setUp(String folderPath) {
        // parse folder
        SequenceParser sp = new SequenceParser();
        specList = sp.parseFolder(folderPath);
    }

    private Node buildBinaryTreeFromTerms(List<Term> terms, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new Node(terms.get(start).getSpec().toString());
        }

        int mid = (start + end) / 2;
        Node node = new Node(null);
        node.setLeftChild(buildBinaryTreeFromTerms(terms, start, mid));
        node.setRightChild(buildBinaryTreeFromTerms(terms, mid + 1, end));
        return node;
    }

    public void addWord(String word, Species spec) {
        TrieNode node = root;
        word = word.toLowerCase();

        for (int c : word.toCharArray()) {
            int letter;
            if (c == (int)'a') {          // A
                letter = 0;
            } else if (c == (int)'c') {   // C
                letter = 1;
            } else if (c == (int)'g') {   // G
                letter = 2;
            } else {                      // T
                letter = 3;
            }

            if (node.getReferences()[letter] == null) {
                node.getReferences()[letter] = new TrieNode();
            }
            node.setPrefixes(node.getPrefixes() + 1);
            node = node.getReferences()[letter];
        }

        node.setPrefixes(node.getPrefixes() + 1);
        node.setWords(1);
        node.setTerm(new Term(word, spec));
    }

    /**
     * Converts data parsed from DNA sequences to a phylogenetic tree.
     * @return root node of tree
     */
    @Override
    public Node buildTree() {
        this.root = new TrieNode();
        for (Species s : specList) {
            // to lowercase and remove any "n" characters
            this.addWord(s.getSequence().toLowerCase().replaceAll("n", ""), s);
        }

        List<Term> terms = this.root.traversal();
        System.out.println("size: " + terms.size());
        this.mainRoot = buildBinaryTreeFromTerms(terms, 0, terms.size() - 1);
        return this.mainRoot;
    }

    public Node getNodeRoot() {
        return this.mainRoot;
    }

    public TrieNode getTrieRoot() {
        return this.root;
    }
}
