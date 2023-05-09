import java.util.List;

/**
 * Implements a TrieTreeBuilder for constructing a phylogenetic tree
 * from a list of DNA sequences.
 */
public class TrieTreeBuilder implements ITreeBuilder {

    private List<Species> specList;
    private Node mainRoot;
    private TrieNode root;

    /**
     * Constructs an empty tree builder
     */
    public TrieTreeBuilder() {}

    /**
     * Constructs a TrieTreeBuilder with a pre-generated species list.
     *
     * @param specList the list of species objects
     */
    public TrieTreeBuilder(List<Species> specList) {
        this.specList = specList;
    }

    /**
     * Sets up necessary components for tree conversion using a given folder path.
     *
     * @param folderPath the path to the folder containing DNA sequence files
     */
    @Override
    public void setUp(String folderPath) {
        // parse folder
        SequenceParser sp = new SequenceParser();
        specList = sp.parseFolder(folderPath);
    }

    /**
     * Recursively builds a binary tree from the given list of terms.
     *
     * @param terms the list of terms for building the tree
     * @param start the start index of the list for the current subtree
     * @param end the end index of the list for the current subtree
     * @return the root node of the binary tree
     */
    private Node buildBinaryTreeFromTerms(List<Term> terms, int start, int end) {
        NameMapper nm = new NameMapper();
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new Node(nm.getName(terms.get(start).getSpec().toString()));
        }

        int mid = (start + end) / 2;
        Node node = new Node(null);
        node.setLeftChild(buildBinaryTreeFromTerms(terms, start, mid));
        node.setRightChild(buildBinaryTreeFromTerms(terms, mid + 1, end));
        return node;
    }

    /**
     * Adds a word and its corresponding species to the trie tree.
     *
     * @param word the word to be added
     * @param spec the species object associated with the word
     */
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
     *
     * @return the root node of the phylogenetic tree
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

    /**
     * Gets the root node of the binary tree representation.
     *
     * @return the root node of the binary tree
     */
    public Node getNodeRoot() {
        return this.mainRoot;
    }

    /**
     * Gets the root node of the trie tree representation.
     *
     * @return the root node of the trie tree
     */
    public TrieNode getTrieRoot() {
        return this.root;
    }
}
