/**
 * @author Harry Smith
 */

public class TrieNode {


    private static final int LETTER_COUNT = 4;
    private Term term;
    private int words;
    private int prefixes;
    private TrieNode[] references;

    /**
     * Initialize a Node with an empty string and 0 weight; useful for
     * writing tests.
     */
    public TrieNode() {
        term = new Term("", null);
        words = 0;
        prefixes = 0;
        references = new TrieNode[LETTER_COUNT];
    }

    /**
     * Initialize a Node with the given query string and weight.
     * @throws IllegalArgumentException if query is null or if weight is negative.
     */
    public TrieNode(String query, Species spec) throws IllegalArgumentException {
        term = new Term(query, spec);
        words = 0;
        prefixes = 0;
        references = new TrieNode[LETTER_COUNT];
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(int prefixes) {
        this.prefixes = prefixes;
    }

    public TrieNode[] getReferences() {
        return references;
    }

    public void setReferences(TrieNode[] references) {
        this.references = references;
    }
}
