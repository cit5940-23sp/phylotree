import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhyloGUI implements ActionListener {

    JButton buttonHuffKruskal;
    JButton buttonTb;
    JButton buttonTrie;
    JButton buttonDNA;
    JButton buttonSpecies;


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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;

        // Add the panels to the frame with GridBagConstraints
        frame.getContentPane().add(panel, gbc);
        frame.getContentPane().add(panel2, gbc);

        // Display the window.
        // frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("D" );
        graph.addNode("E" );
        graph.addNode("S");
        graph.addNode("X");
        graph.addEdge("BS", "B", "S");
        graph.addEdge("BX", "B", "X");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AC", "A", "C");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("CE", "C", "E");
        System.setProperty("org.graphstream.ui", "swing");

        String verts = "DESX";
        for (char i : verts.toCharArray()) {
            String vertid = String.valueOf(i);
            Node n = graph.getNode(vertid);
            n.setAttribute("ui.style", "text-mode: normal;" +
                    "text-background-mode: plain;" +
                    "text-alignment: under;" +
                    "text-size: 40;");
            n.setAttribute("ui.label", "Node " + vertid);
            n.edges().forEach(System.out::println);
        }

        PhyloGUI gui = new PhyloGUI();
        //Viewer view = graph.display();
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> gui.createAndShowGUI());
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
}

