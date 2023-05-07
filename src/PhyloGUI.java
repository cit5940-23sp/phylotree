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


public class PhyloGUI implements ActionListener {

    SwingViewer graphViewer;
    PhyloTree phyloTree;

    JButton buttonHuffKruskal;
    JButton buttonTb;
    JButton buttonTrie;
    JButton buttonDNA;
    JButton buttonSpecies;

    PhyloGUI(PhyloTree tree) {
        this.phyloTree = tree;
        this.graphViewer = new SwingViewer(tree.getGraphStream(), SwingViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);;
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
        this.buttonTb = new JButton("Textbook");
        this.buttonTb.addActionListener(this);
        panel.add(buttonTb);
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
        //viewerPanel.setBorder(BorderFactory.createTitledBorder("Tree View"));
        viewerPanel.add((Component) view, BorderLayout.CENTER);

        // Add the Viewer JPanel to the frame
        frame.getContentPane().add(viewerPanel, gbcViewer);

        // Display the window.
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonHuffKruskal) {
            System.out.println("krus");
        } else if (e.getSource() == this.buttonTb) {
            System.out.println("textbook");
        } else if (e.getSource() == this.buttonTrie) {
            System.out.println("trie");
        } else if (e.getSource() == this.buttonDNA) {
            System.out.println("dna");
        } else if (e.getSource() == this.buttonSpecies) {
            System.out.println("species");
        }
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        Node root = new Node(new Node(null), new Node("Leaf D"));
        root.getLeftChild().setLeftChild(new Node("Leaf A"));
        root.getLeftChild().setRightChild(new Node(null));
        root.getLeftChild().getRightChild().setLeftChild(new Node("Leaf B"));
        root.getLeftChild().getRightChild().setRightChild(new Node("Leaf C"));
        PhyloTree pt = new PhyloTree(root);
        pt.buildGraphStream();
        PhyloGUI gui = new PhyloGUI(pt);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }


}

