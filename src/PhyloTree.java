// https://en.wikipedia.org/wiki/File:NewickExample.svg
//
// (A:0.1,B:0.2,(C:0.3,D:0.4):0.5);
// distances and leaf names (popular)
//

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a phylogenetic tree with methods to find nearest neighbors
 * of given species by species name or DNA sequence, and to visualize
 * the tree using GraphStream library.
 */
public class PhyloTree {
    private Node root;
    private Graph graph_stream;
    private int count = 0;
    private final String GRAPH_STREAM_ID = "PhyloTree";
    private NameMapper nameMapper;

    /**
     * Constructs a PhyloTree object from a given Node.
     * @param node the root node of the phylogenetic tree
     */
    PhyloTree(Node node) {
        this.root = node;
        this.graph_stream = new SingleGraph(GRAPH_STREAM_ID);
        this.nameMapper = new NameMapper();
    }


    /**
     * Highlights the specified nodes in the graph.
     * @param query
     */
    public void highlight(List<String> query) {
        for (String nodeId : query) {
            org.graphstream.graph.Node node = graph_stream.getNode(nodeId);
            if (node != null) {
                node.setAttribute("ui.style", "text-mode: normal;" +
                        "text-background-mode: plain;" +
                        "text-alignment: under;" +
                        "text-size: 20;" +
                        "text-color: red;");
            }
        }
    }


    /**
     * Finds the top 5 nearest neighbors of a given species by species name.
     * @param scientificName
     * @param speciesList
     * @param editDistance
     * @return
     */
    public List<String> nearestBySpeciesName(String scientificName, List<Species> speciesList, int[][] editDistance) {
        int id = -1;  // id for the item

        List<String> closestSpecies = new ArrayList<>();
        List<Map.Entry<Integer,Integer>> closestIDs = new ArrayList<>();

        for (int i = 0;i < speciesList.size();i++) {
            if (speciesList.get(i).toString().equals(scientificName)) {
                id = speciesList.get(i).getID();
                break;
            }
        }

        //species not found
        if (id == -1) {
            return closestSpecies;
        }

        //add the closest species objects
        for (int i = 0; i < editDistance.length;i++) {
            closestIDs.add(Map.entry(i,editDistance[id][i]));
        }

        //sort in the ascending order of IDs
        Collections.sort(closestIDs, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));

        //get the species names
        for (int i = 0; i < closestIDs.size();i++) {
            int ID = closestIDs.get(i).getKey();
            closestSpecies.add(this.nameMapper.getName(speciesList.get(ID).toString()));
        }

        //return the top 5 species
        int len = Math.min(closestSpecies.size(), 5);
        return closestSpecies.subList(0, len);
    }


    /**
     * Finds the top 5 nearest neighbors of a given species by DNA sequence.
     * @param sequence
     * @param speciesList
     */
    public List<String> nearestBySequence(String sequence, List<Species> speciesList) {
        List<String> closestSpecies = new ArrayList<>();
        List<Map.Entry<Integer,Integer>> closestIDs = new ArrayList<>();

        // create a new species object
        Species newSpecies = new Species();
        newSpecies.setGenusName("New Species");
        newSpecies.setID(speciesList.size());
        newSpecies.setSequence(sequence);

        // array for edit distance
        int[] editDistance = new int[speciesList.size()];

        // compute and store the edit distances for the new species
        EditDistance ed = new EditDistance(speciesList);
        for (int i = 0; i < speciesList.size();i++) {
            editDistance[i] = ed.editDist(newSpecies,speciesList.get(i));
        }

        // add the closest species objects
        for (int i = 0; i < editDistance.length;i++) {
            closestIDs.add(Map.entry(i,editDistance[i]));
        }

        // sort in the ascending order of IDs
        Collections.sort(closestIDs, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));

        // get the species names
        for (int i = 0; i < closestIDs.size();i++) {
            int ID = closestIDs.get(i).getKey();
            closestSpecies.add(this.nameMapper.getName(speciesList.get(ID).toString()));
        }

        int len = Math.min(closestSpecies.size(), 5);
        return closestSpecies.subList(0, len);
    }

    private boolean isLeaf(Node node) {
        return node.getLeftChild() == null && node.getRightChild() == null;
    }

    /**
     * Builds the GraphStream graph representation of the
     * phylogenetic tree in an in-order traversal.
     * @param node
     */
    private void buildGraphStreamInOrder(Node node)
    {
        if (node == null)
            return;

        String id;

        // left child
        buildGraphStreamInOrder(node.getLeftChild());

        // give node a unique id
        if (isLeaf(node)) {
            // if leaf, use species name
            id = node.getSpecName();
            org.graphstream.graph.Node n = this.graph_stream.addNode(node.getSpecName());
            // visual attributes for leaf nodes
            n.setAttribute("ui.style", "text-mode: normal;" +
                    "text-background-mode: plain;" +
                    "text-alignment: under;" +
                    "text-size: 20;");
            n.setAttribute("ui.label", id);
            node.setId(id);
        } else {
            // ow use unique count
            id = String.valueOf(count++);
            this.graph_stream.addNode(id);
            node.setId(id);
        }

        // right child
        buildGraphStreamInOrder(node.getRightChild());

        if (!isLeaf(node)) {
            // add edges
            this.graph_stream.addEdge(id + ":" +  node.getLeftChild().getId(),
                    id, node.getLeftChild().getId());
            this.graph_stream.addEdge(id + ":" +  node.getRightChild().getId(),
                    id, node.getRightChild().getId());
        }
    }

    /**
     * Constructs the GraphStream graph representation of
     * the phylogenetic tree.
     */
    public void buildGraphStream() {
        graph_stream.clear();
        this.count = 0;
        buildGraphStreamInOrder(this.root);
    }

    public void setRoot(Node node) {
        this.root = node;
    }

    public Graph getGraphStream() {
        return this.graph_stream;
    }
}
