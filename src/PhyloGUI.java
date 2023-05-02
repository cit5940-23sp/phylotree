import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;

public class PhyloGUI {

    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("D" );
        graph.addNode("E" );
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AC", "A", "C");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("CE", "C", "E");
        System.setProperty("org.graphstream.ui", "swing");

        String verts = "ABCDE";
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

        graph.display();
    }
}

