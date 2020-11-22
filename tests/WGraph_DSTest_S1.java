package ex1.tests;
import ex1.src.NodeInfo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest_S1 {
    List<node_info> nodes= new ArrayList<>();
    List<weighted_graph> w_graphs= new ArrayList<>();
    WGraph_DS g1= new WGraph_DS();
    WGraph_DS nullGraph= new WGraph_DS();

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    long start = new Date().getTime();
    long end = new Date().getTime();

    @BeforeEach
    void setUp() {
        start = new Date().getTime();
        System.out.println(ANSI_GREEN+"Setting up the environment. creating the graphs..."+ANSI_RESET);
        double di;
        Integer obj = 0;
        String si = null;
        for (int i = 0; i < 20; i++) { //create a new array of nodes, with new nodes
            nodes.add(new NodeInfo(i));
        }
        for (int i = 0; i < 20; i++) { //initializing the nodes with tags and infos.
            di = i;
            obj = i;
            si = obj.toString();
            nodes.get(i).setTag(di + 0.5);
            nodes.get(i).setInfo(si + "+0.5");
        }

        for (int i = 0; i < 20; i++) { //initialize a new graph of nodes, with new nodes
            g1.addNode(i);
        }

        g1.connect(1,3,5);
       // int edgeExp= 5;
        g1.connect(1,4,2);
        g1.connect(2,4,7);
        g1.connect(1,5,6);
        g1.connect(2,9,10);

    }

    @AfterEach
    void tearDown() {
        end = new Date().getTime();
        double dt = (end-start)/1000.0;
        System.out.println("This test took "+dt+" seconds");
    }

    @Test
    void getNode() {
        System.out.println(ANSI_GREEN+"Checking if the keys of nodes are as expected,"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"and if after removing a node from a graph it's as expected..."+ANSI_RESET);

        for (int i = 0; i < 20; i++) {
            assertEquals(i, nodes.get(i).getKey()); //see if the keys of nodes are as expected
        }
        g1.removeNode(1);
        assertNull(g1.getNode(1));

        nullGraph.removeNode(1);
        assertNull(nullGraph.getNode(1));

    }

    @Test
    void hasEdge() {
        System.out.println(ANSI_GREEN+"Checking if removing edges really does it, "+ANSI_RESET);
        System.out.println(ANSI_GREEN+"and if hasEdge on non-existent nodes work as expected..."+ANSI_RESET);
        assertTrue(g1.hasEdge(1, 3)); //has Edge
        assertFalse(g1.hasEdge(1, 6)); //has no Edge
        assertFalse(g1.hasEdge(40, 3)); //non-existent node, no edge



    }

    @Test
    void getEdge() {
        System.out.println(ANSI_GREEN+"Checking if getting edges that exist and not,"+ANSI_RESET);
        System.out.println(ANSI_GREEN+" with nodes that exist and do not creates bugs..."+ANSI_RESET);
        g1.getEdge(1,4); //exist
        g1.getEdge(2,5); //doesnt exist
        g1.getEdge(30,40); //both nodes don't exist
        g1.getEdge(1,40); //one of the nodes doesn't exist

    }

    @Test
    void addNode() {
        System.out.println(ANSI_GREEN+"Checking if adding nodes with keys that exist and do not creates bugs..."+ANSI_RESET);
        g1.addNode(1); //already exist
        g1.addNode(30); //does not exist, should add this one
    }

    @Test
    void connect() {
        System.out.println(ANSI_GREEN+"Checking if removing edges that exist and do not creates bugs,"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"as if wanting-to create an already edge does change the weight- as it should.."+ANSI_RESET);
        System.out.println("g1.getEdge now is"+g1.getEdge(1,3));
        g1.connect(1,3,6); //CHECK IF IT CHANGES THE ALREADY EXISTED EDGE WEIGHT!
        assertEquals(g1.getEdge(1,3), 6); //it should change and be 6 now!
        g1.connect(40,2,10); //CHECK IF IT FAILES WHEN TRYING TO CONNECT A NON-EXISTENT NODE
        g1.connect(2,40,10); //^MAYBE IN ANOTHER ORDER
        g1.connect(3,57,10); //^OR TWO

    }

    @Test
    void getV() {
        System.out.println(ANSI_GREEN+"Checking if getV really retrieves all and only the nodes in the graph..."+ANSI_RESET);
        Collection<node_info> nodeCol= g1.getV();
        node_info a=null;
        int countNodes=0;
        for (int i=0; i<g1.nodeSize; i++){
            a=g1.getNode(i);
            if (!nodeCol.contains(a)){
                countNodes=-99;
            }
        countNodes++;
        }
        assertEquals(countNodes, nodeCol.size());
////////////////////**checking with a nullGraph too*******//////////////////////
 System.out.println(ANSI_GREEN+"Checking with a null graph too..."+ANSI_RESET);

        Collection<node_info> nodeColnull= nullGraph.getV();
        node_info anull=null;
        int countNodesnull=0;
        for (int i=0; i<nullGraph.nodeSize; i++){
            anull=nullGraph.getNode(i);
            if (!nodeColnull.contains(anull)){
                countNodesnull=-99;
            }
            countNodesnull++;
        }
        assertEquals(countNodesnull, nodeColnull.size());

}

//    @Test --TOCHECK: what's this auto-generated test supposed to test different than the above one?
//
//    void testGetV() {
//
//    }

    @Test
    void removeNode() {
        System.out.println(ANSI_GREEN+"Checking if removing nodes that exist and do not creates bugs..."+ANSI_RESET);

        g1.removeNode(3);
        g1.removeNode(5);
        g1.removeNode(7);
        g1.removeNode(29); //doesnt exist
        for (int i = 0; i < 20; i++) {
            if(i!=3&& i!=5&& i!=7&& i!=10) {
                assertEquals(i, nodes.get(i).getKey()); //see if the keys of nodes are as expected after deletions
            }
        }
    }

    @Test
    void removeEdge() {
        System.out.println(ANSI_GREEN+"Checking if removing edges that do not exist cause a bug..."+ANSI_RESET);

        g1.removeEdge(2,4); //existent node
        g1.removeEdge(1,9); //non existent node

    }

    @Test
    void nodeSize() {
        System.out.println(ANSI_GREEN+"Checking if the nodeSize changes properly after removing nodes"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"that exist and do not..."+ANSI_RESET);

        int nodeSize= g1.nodeSize;
        g1.removeNode(3); //removing an existent node, should decrease the g1.nodeSize
        g1.removeNode(5); //^
        g1.removeNode(3); //removing the same node, should NOT decrease the g1.nodeSize
        assertEquals(nodeSize-2, g1.nodeSize);

        System.out.println(ANSI_GREEN+"Checking with a null graph too"+ANSI_RESET);

        int nodeSizenull= nullGraph.nodeSize;
        nullGraph.removeNode(3); //removing an existent node, should decrease the g1.nodeSize
        nullGraph.removeNode(5); //^
        nullGraph.removeNode(3); //removing the same node, should NOT decrease the g1.nodeSize
        assertEquals(nodeSizenull, nullGraph.nodeSize);

    }

    @Test
    void edgeSize() {
        System.out.println(ANSI_GREEN+"Checking if the edgeSize changes properly after removing edges"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"that exist and do not, between nodes that exists and don't..."+ANSI_RESET);
        int edgesNum= g1.edgeSize();
        g1.removeEdge(2,4); //removes an existing edge, should decrease the g1.edgesize by one
        g1.removeEdge(3,4); //removes a non-existing edge, should NOT decrease the g1.edgesize
        g1.removeEdge(3,3); //removes a non-existing edge, by node to itself, should NOT decrease the g1.edgesize
        g1.removeEdge(3,57); //removes a non-existing edge, between exist node and not, should NOT decrease the g1.edgesize
        g1.removeEdge(56,57); //removes a non-existing edge, between two not existent, should NOT decrease the g1.edgesize

        assertEquals(g1.edgeSize()+1, edgesNum);
        assertEquals(g1.edgeSize(), 4); //as it should be 4

    }

    @Test
    void getMC() {
        System.out.println(ANSI_GREEN+"Checking if the MC changes properly after removing edges"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"that exist and do not, between nodes that exists and don't..."+ANSI_RESET);
        int mc= g1.getMC();
        g1.removeEdge(2,4); //removes an existing edge, should decrease the g1.MC by TWO
        // cuz it changes the neighbors of TWO nodes. it's up for debate.
        g1.removeEdge(3,4); //(does not) removes a non-existing edge, should NOT decrease the g1.MC
        g1.removeEdge(3,40); //(does not) removes a non-existing edge, should NOT decrease the g1.MC
        g1.removeEdge(3,4); //(does not) removes a non-existing edge, should NOT decrease the g1.MC

        assertEquals(mc+2, g1.getMC());

    }

} //last one standing