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

    public int getChildrenSize() {
        int count = 0;
        for (int i = 0; i < LETTER_COUNT; i++) {
            if (references[i] != null) {
                count++;
            }
        }
        return count;
    }

    public TrieNode getChild(int c) throws IndexOutOfBoundsException {
        int size = this.getChildrenSize() - 1;
        if (c > size || c < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < LETTER_COUNT; i++) {
            if (references[i] != null) {
                size--;
                if (size == 0) {
                    return references[i];
                }
            }
        }

        return null;
    }

    public List<Term> traversal() {
        return traversalIterative(this);
    }

     List<Term> traversalIterative(TrieNode itr) {
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



    private void inOrderTraversalHelper(TrieNode node, List<Term> terms) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < LETTER_COUNT; i++) {
            TrieNode child = node.references[i];
            if (child != null) {
                inOrderTraversalHelper(child, terms);
                if (child.getWords() == 1) {
                    terms.add(child.getTerm());
                }
            }
        }
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
