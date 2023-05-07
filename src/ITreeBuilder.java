public interface ITreeBuilder {
    /**
     * Sets up necessary components for tree conversion
     * @param folderPath
     * @return
     */
    void setUp(String folderPath);
    
    /**
     * Converts data parsed from DNA sequences to a phylogenetic tree.
     * @return root node of tree
     */
    Node buildTree();
}
