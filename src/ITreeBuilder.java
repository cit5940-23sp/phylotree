public interface ITreeBuilder {
    /**
     * Sets up necessary components for tree conversion
     * @param folderPath
     * @return
     */
    public static void setUp(String folderPath) {
        
    }
    
    /**
     * Converts the
     * @param matrix
     * @return
     */
    public Node buildTree();
}
