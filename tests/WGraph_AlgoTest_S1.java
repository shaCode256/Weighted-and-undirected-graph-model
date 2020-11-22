package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest_S1 {
    List<node_info> nodes= new ArrayList<>();
    List<weighted_graph> w_graphs= new ArrayList<>();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    WGraph_DS g1= new WGraph_DS();
    WGraph_DS g2= new WGraph_DS();
    WGraph_DS millions= new WGraph_DS();
    weighted_graph_algorithms ag1 = null;
    weighted_graph_algorithms ag2 = null;
    weighted_graph_algorithms ag3= null;

    long start = new Date().getTime();

    @BeforeEach
    void setUp() {
        start = new Date().getTime();
        System.out.println(ANSI_GREEN+"Setting up the environment. creating the graphs..."+ANSI_RESET);
        double di;
        Integer obj=0;
        String si=null;
        for(int i=0;i<20; i++){ //create a new array of nodes, with new nodes
            nodes.add(new NodeInfo(i));
        }
        for(int i=0;i<20; i++){ //initializing the nodes with tags and infos.
            di= i;
            obj = i;
            si = obj.toString();
            nodes.get(i).setTag(di+0.5);
            nodes.get(i).setInfo(si+"+0.5");
        }
//////////////////////////////////////////////starting creating graph1/////////////////////
        for (int i = 0; i < 20; i++) { //initialize a new graph of nodes, with new nodes
            g1.addNode(i);
        }
        g1.connect(1,3,5);
        // int edgeExp= 5;
        g1.connect(1,4,0.1);
        g1.connect(2,4,0.1);
        g1.connect(1,5,12);
        g1.connect(2,9,10);
        g1.connect(2,5,1);

        ag1 = new WGraph_Algo();
        ag1.init(g1);
//////////////////////////////////////////////end of creating graph1/////////////////////

//////////////////////////////////////////////starting creating graph2/////////////////////

        ag2 = new WGraph_Algo();

        for (int i = 0; i < 3; i++) { //initialize a new graph of nodes, with new nodes
            g2.addNode(i);
        }

        g2.connect(0,1, 3);
        g2.connect(0,2, 4);
        g2.connect(2,1, 2);

        ag2.init(g2);

        //////////////////////////////////////////////end of creating graph2/////////////////////

//////////////////////////////////////////////starting creating graph3/////////////////////

        ag3 = new WGraph_Algo();

        //////////////////////////////////////////////end of creating graph3/////////////////////


    }

    @AfterEach
    void tearDown() {
        long end = new Date().getTime();
        double dt = (end-start)/1000.0;
        System.out.println("This test took "+dt+" seconds");

    }

    @Test
    void init() {
        assertEquals(ag1.getGraph() ,g1); //the getGraph will be correct if&only the init is correct.

    }

    @Test
    void getGraph() {
        System.out.println(ANSI_GREEN+"Checking if the getGraph works ok..."+ANSI_RESET);
        assertEquals(ag1.getGraph(), g1);
    }

    @Test
    void copy() {
        System.out.println(ANSI_GREEN+"Checking if the copy method works ok..."+ANSI_RESET);
        //going through all the graph, seeing how mny nodes in both of them, and then iterating
        //on both to see those with the same key, and than count how many there are,
        //then we see if their infos and tags are equal as needed. if there were exactly same
        //nodes with the same keys in both of them and this all was true (if tag/info was different than
        //the count would mess up as I programmed it to), than it's really a deep copy.

        weighted_graph copied =ag1.copy();
        int countNodes = 0;
        //if (copied.getV().size()== copied.nodeSize()){
        //    assertEquals("NODESIZE=GETV.SIZE- IMPORTANT CHECK");
        //} could've done an assert but with a null graph it will be 0:null so nope.

        if (copied.getV().size()==g1.getV().size()) {
            for (node_info copnode : copied.getV()) {
                for (node_info orgnode : g1.getV()) {
                    if (copnode.getKey() == orgnode.getKey()) {
                        countNodes++;
                        if (copnode.getTag() != orgnode.getTag()) {
                            countNodes = -99;
                        }
                        if (copnode.getInfo() != orgnode.getInfo()) {
                            countNodes = -99;
                        }
                    }
                }
            }
        }
        assertEquals(countNodes, g1.nodeSize);
    }

    @Test
    void isConnected() {
        System.out.println(ANSI_GREEN+"Checking if the isConnected works ok..."+ANSI_RESET);
        assertFalse(ag1.isConnected()); //ag1 is not connected.
        assertTrue(ag2.isConnected()); //ag2 is connected.

    }

    @Test
    void shortestPathDist() {
        System.out.println(ANSI_GREEN+"Checking if the shortestPathDist works ok..."+ANSI_RESET);
        assertEquals(ag1.shortestPathDist(2,5),1);

    }

    @Test
    void ifGetVWorks(){
        System.out.println("meaningGetVWorks");
        System.out.println("the neighbors are "+g1.getV(2));
    }
    @Test
    void shortestPath() {
        System.out.println(ANSI_GREEN+"Checking if the shortestPath works ok..."+ANSI_RESET);
        assertEquals(ag1.shortestPath(2,5).size(),2.0);
        List <node_info> listy=ag1.shortestPath(2,5);
        System.out.println("The path is: ");
        System.out.print("start --> ");
        for (int i=0; i<listy.size(); i++) { //print the path!
            System.out.print(listy.get(i).getKey()+" --> ");
        }
        System.out.print("end");
        System.out.println();

    }

    @Test
    void save() {
        int willItWork=0;
        String file="C:\\Users\\97252\\OneDrive\\שולחן העבודה\\Ex1\\src\\ex1\\src\\filemame";
        System.out.println(ANSI_GREEN+"Checking if the saving method works ok..."+ANSI_RESET);
        System.out.println(ANSI_GREEN+" Iterating through the saved graph obj as a copy and comparing..."+ANSI_RESET);
        try {
            ag1.save(file);
           // System.out.println("getV of two is"+g1.getV(2));
        } catch (Exception e) {
            e.printStackTrace();
            willItWork=1;
        }
    assertEquals(0, willItWork);

    }

    @Test
    void testLoad(){
        boolean a=ag1.save("C:\\Users\\97252\\OneDrive\\שולחן העבודה\\Ex1\\src\\ex1\\src\\hey");
        boolean b=ag1.load("C:\\Users\\97252\\OneDrive\\שולחן העבודה\\Ex1\\src\\ex1\\src\\hey");
        assertTrue(a, String.valueOf(true));
        assertTrue(b, String.valueOf(true));

    }

    @Test
    void load() {
        System.out.println(ANSI_GREEN + "Checking if the loading method works ok..." + ANSI_RESET);
        System.out.println(ANSI_GREEN + " Iterating through the loaded graph obj as a copy and comparing..." + ANSI_RESET);
        int willItWork = 0;
        int countNodes = 0;
        String file = "C:\\Users\\97252\\OneDrive\\שולחן העבודה\\Ex1\\src\\ex1\\src\\filemame";
        try {
            ag3.load(file);
            weighted_graph copied= ag3.getGraph();
            if (copied.getV().size() == g1.getV().size()) {
                for (node_info copnode : copied.getV()) {
                    for (node_info orgnode : g1.getV()) {
                        if (copnode.getKey() == orgnode.getKey()) {
                            countNodes++;
                            if (copnode.getTag() != orgnode.getTag()) {
                                countNodes = -99;
                            }
                            if (copnode.getInfo() != orgnode.getInfo()) {
                                countNodes = -99;
                            }
                        }
                    }
                }
            }

            assertEquals(countNodes, g1.nodeSize);
        } catch (Exception e) {
            e.printStackTrace();
            willItWork = 1;
        }
        assertEquals(0, willItWork);
        assertEquals(countNodes, g1.nodeSize); //toMakeSure

        //now let's compare the loaded and saved graphs: both should be graph g1.

    }

        WGraph_DS graph_creator(int nodes, int edges) {
        WGraph_DS graph= new WGraph_DS();
        System.out.println(ANSI_GREEN+"Creating a graph..."+ANSI_RESET);
       // assertFalse(ag1.isConnected()); //ag1 is not connected.
   //     System.out.println(ANSI_GREEN+"Checking if isConnected on graph with miliions of nodes works ok..."+ANSI_RESET);
   //     System.out.println(ANSI_GREEN+"Checking if isConnected on graph with miliions of nodes works ok..."+ANSI_RESET);

        for(int i=0;i<nodes;i++) { //add 1000000 nodes
            graph.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        Random ran = new Random();
        int range = nodes + 1;     //connect 10000000 edges
        int randomNum;
        while(graph.edgeSize() < edges) {
            randomNum =  ran.nextInt(range);
            int a = randomNum;
            randomNum =  ran.nextInt(range);
            int b = randomNum;
            randomNum =  ran.nextInt(range);
            double w = randomNum;
            graph.connect(a,b, w);
        }

        return graph;
  }

  @Test

    void checkAlgosOnBigGraphs(){
      System.out.println(ANSI_GREEN+"Checking the algorithms on a new big graph..."+ANSI_RESET);
      String file= "checkAlgoBigFile";
        WGraph_DS graph=graph_creator(10000, 5000);
      ag1 = new WGraph_Algo();
      ag1.init(graph);
      ag1.isConnected();
      ag1.save(file);
      ag1.load(file);
      ag1.copy();
      double dist=ag1.shortestPathDist(0,3);
      System.out.println("shortestPathDist is "+dist);
      List<node_info> listy= ag1.shortestPath(0,3);
      System.out.println("The path is:");
      for (int i=0; i<listy.size(); i++) {
          System.out.print(listy.get(i)+ " --> ");
      }
    }

    @Test
    void millionsGraph() {

        System.out.println(ANSI_GREEN+"Checking if building graph with miliions of nodes works ok..."+ANSI_RESET);
       // assertFalse(ag1.isConnected()); //ag1 is not connected.
        System.out.println(ANSI_GREEN+"Checking if isConnected on graph with miliions of nodes works ok..."+ANSI_RESET);
        System.out.println(ANSI_GREEN+"Checking if isConnected on graph with miliions of nodes works ok..."+ANSI_RESET);

        for(int i=0;i<1000000;i++) { //add 1000000 nodes
            millions.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        Random ran = new Random();
        int range = 999999 - 0 + 1;     //connect 10000000 edges
        int randomNum;
        while(millions.edgeSize() < 10000000) {
            randomNum =  ran.nextInt(range) + 0;
            int a = randomNum;
            randomNum =  ran.nextInt(range) + 0;
            int b = randomNum;
            randomNum =  ran.nextInt(range) + 0;
            double w = randomNum;
            millions.connect(a,b, w);
        }
  }


}// last one standing