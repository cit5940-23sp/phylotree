import java.util.List;

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

        return null;
    }

    public TrieNode getSubTrie(String prefix) {
        TrieNode node = root;
        for (int c : prefix.toLowerCase().toCharArray()) {
            if (!Character.isLetter(c)) {
                return null;
            }
            int letter = c - 'a';
            if (node.getReferences()[letter] == null) {
                return null;
            }
            node = node.getReferences()[letter];
        }
        return node;
    }
}
