import java.util.*;

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

    public List<Term> traversal() {
        return traversalIterative(this);
    }

    private List<Term> traversalIterative(TrieNode itr) {
        Stack<TrieNode> stack = new Stack<>();
        List<Term> terms = new ArrayList<>();

        // Push the root node into the stack
        stack.push(itr);

        // Loop until the stack is empty
        while (!stack.isEmpty()) {
            TrieNode current = stack.pop();

            // Check base condition
            if (current.getWords() == 1) {
                terms.add(current.getTerm()); // Print character
            }

            // Loop for checking the value of character
            for (int i = 0; i < 4; i++) {
                // If a reference is not null, push it onto the stack
                if (current.getReferences()[i] != null) {
                    stack.push(current.getReferences()[i]);
                }
            }
        }
        return terms;
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
