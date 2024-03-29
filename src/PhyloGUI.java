import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class PhyloGUI implements ActionListener {

    private final String FOLDER_PATH;
    private List<Species> specList;
    private int[][] editDistanceMatrix;

    private SwingViewer graphViewer;
    private PhyloTree phyloTree;

    private JButton buttonHuffKruskal;
    private JButton buttonTrie;
    private JButton buttonDNA;
    private JButton buttonSpecies;
    private String queryText;

    PhyloGUI(String folderPath) {
        FOLDER_PATH = folderPath;
        this.queryText = "";
    }

    private JMenuBar createMenuBar() {
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        // Add menu items to the file menu
        fileMenu.add(new JMenuItem("Import Tree"));
        fileMenu.add(new JMenuItem("Export Tree"));

        // Add menu items to the help menu
        helpMenu.add(new JMenuItem("About"));

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("PhyloTree Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Set the menu bar for the frame
        frame.setJMenuBar(createMenuBar());

        frame.getContentPane().setLayout(new GridBagLayout());

        // build tree panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 5, 10));
        panel.setBorder(new TitledBorder("Build Tree"));
        this.buttonHuffKruskal = new JButton("Huff-Kruskal");
        this.buttonHuffKruskal.addActionListener(this);
        panel.add(buttonHuffKruskal);
        this.buttonTrie = new JButton("Trie");
        this.buttonTrie.addActionListener(this);
        panel.add(buttonTrie);

        // query panel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3, 5, 10));
        panel2.setBorder(new TitledBorder("Query"));
        this.buttonDNA = new JButton("By DNA Sequence");
        this.buttonDNA.addActionListener(this);
        panel2.add(buttonDNA);
        this.buttonSpecies = new JButton("By Species Name");
        this.buttonSpecies.addActionListener(this);
        panel2.add(buttonSpecies);

        // add panel to layout
        frame.add(panel);
        frame.add(panel2);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new GridBagLayout());

        // Create GridBagConstraints
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.anchor = GridBagConstraints.SOUTH;
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.weighty = 0;
        gbcPanel.weightx = 1;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;

        GridBagConstraints gbcPanel2 = new GridBagConstraints();
        gbcPanel2.anchor = GridBagConstraints.SOUTH;
        gbcPanel2.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel2.weighty = 0;
        gbcPanel2.weightx = 1;
        gbcPanel2.gridx = 1;
        gbcPanel2.gridy = 1;

        GridBagConstraints gbcViewer = new GridBagConstraints();
        gbcViewer.anchor = GridBagConstraints.NORTH;
        gbcViewer.fill = GridBagConstraints.BOTH;
        gbcViewer.weighty = 1;
        gbcViewer.weightx = 1;
        gbcViewer.gridx = 0;
        gbcViewer.gridy = 0;
        gbcViewer.gridwidth = 2; // Span two columns

        // Add the panels and the Viewer to the frame with GridBagConstraints
        frame.getContentPane().add(panel, gbcPanel);
        frame.getContentPane().add(panel2, gbcPanel2);

        // Create and configure the SwingViewer
        graphViewer.enableAutoLayout();
        View view = graphViewer.addDefaultView(false);

        // Create a JPanel to hold the Viewer component
        JPanel viewerPanel = new JPanel(new BorderLayout());
        viewerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        viewerPanel.add((Component) view, BorderLayout.CENTER);

        // Add the Viewer JPanel to the frame
        frame.getContentPane().add(viewerPanel, gbcViewer);

        // Display the window.
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs (one of the buttons are pressed)
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonHuffKruskal) {
            // Huffman-Kruskal method
            System.out.println("krus");
            HuffKruskalTreeBuilder hktb = new HuffKruskalTreeBuilder(
                    this.specList,
                    this.editDistanceMatrix
            );
            Node root = hktb.buildTree();
            this.phyloTree.setRoot(root);
            this.phyloTree.buildGraphStream();
        } else if (e.getSource() == this.buttonTrie) {
            // Trie method
            System.out.println("trie");
            TrieTreeBuilder ttb = new TrieTreeBuilder(this.specList);
            Node root = ttb.buildTree();
            this.phyloTree.setRoot(root);
            this.phyloTree.buildGraphStream();
        } else if (e.getSource() == this.buttonDNA) {
            // query by DNA Sequence
            System.out.println("dna");
            showQueryPopup("Query by DNA Sequence");
            List<String> query = this.phyloTree.nearestBySequence(this.queryText, this.specList);
            this.phyloTree.highlight(query);
        } else if (e.getSource() == this.buttonSpecies) {
            // query by species name
            System.out.println("species");
            showQueryPopup("Query by Species Name (Scientific Name)");
            System.out.println(queryText);
            List<String> query = this.phyloTree.nearestBySpeciesName(
                    this.queryText, this.specList, this.editDistanceMatrix);
            this.phyloTree.highlight(query);
        }
    }

    private void showQueryPopup(String title) {
        // Create a JDialog
        JDialog queryDialog = new JDialog();
        queryDialog.setTitle(title);
        queryDialog.setModal(true);
        queryDialog.setSize(400, 300);
        queryDialog.setLocationRelativeTo(null);
        queryDialog.setLayout(new BorderLayout());

        // Create a JTextArea for the editable textbox
        JTextArea textArea = new JTextArea();
        queryDialog.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create a submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            this.queryText = textArea.getText();
            queryDialog.dispose();
        });
        queryDialog.add(submitButton, BorderLayout.SOUTH);

        // Show the JDialog
        queryDialog.setVisible(true);
    }

    public void run() {
        System.setProperty("org.graphstream.ui", "swing");
        SequenceParser sp = new SequenceParser(260);

        // get spec list + edit distance
        this.specList = sp.parseFolder(FOLDER_PATH);  // set path
        EditDistance ed = new EditDistance(specList);
        this.editDistanceMatrix = ed.editDistMatrix();

        // make empty phylo tree
        PhyloTree pt = new PhyloTree(new Node(""));

        this.phyloTree = pt;
        this.graphViewer = new SwingViewer(
                pt.getGraphStream(),
                SwingViewer.ThreadingModel.GRAPH_IN_GUI_THREAD
        );
        javax.swing.SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    public static void main(String[] args) {
        PhyloGUI pt = new PhyloGUI("sequences");
        pt.run();
    }


}

