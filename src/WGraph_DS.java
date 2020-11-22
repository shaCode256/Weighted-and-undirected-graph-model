package ex1.src;

//import ex0.graph;

import java.io.Serializable;
import java.util.*;

/**
 * This interface represents an undirectional unweighted graph.
 * It should support a large number of nodes (over 10^6, with average degree of 10).
 * The implementation should be based on an efficient compact representation
 *
 */

//Has a HashMap represting all the nodes in the graph,
//with integers of nodes as KEYS and their node_infos as VALUES.

public class WGraph_DS
        implements weighted_graph, Serializable {

    private final HashMap<Integer, node_info> nodekeys= new HashMap<Integer, node_info>(); //this contains a hashmap of all the nodes, their keys and node_infos.
    private final HashMap<Integer, HashMap< Integer, Double>> edges= new HashMap<Integer, HashMap<Integer, Double>>();
    //^A hashmap with KEYS as nodes keys, VALUES of hashmaps with VALUE of neighbor nodes keys,
    //AND the size of the edge between them as VALUES.

    public int mc = 0;
    public int nodeSize =0;
    public int edgeSize=0;
    public int key=0;
    /**
     * return the node_info by the node_id,
     *
     //  * @param key - the node_id
     * @return the node_info by the node_id, null if none.
     */

    public WGraph_DS() {
        HashMap<Integer, node_info> nodekeys= new HashMap<Integer, node_info>();
        HashMap<Integer, HashMap< Integer, Double>> edges= new HashMap<Integer, HashMap<Integer, Double>>();
        int count=0;
        int key=0;
        int mc = 0;
        int nodeSize =0;
        int edgeSize=0;
    }

    /**
     * return the node_info by the node_id,
     * @param key - the node_id
     * @return the node_info by the node_id, null if none.
     *
     */
    public node_info getNode(int key) {
        return nodekeys.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
    public boolean hasEdge(int node1, int node2){
        if(this.nodekeys.isEmpty()){
            return false;
        }
        if(!this.nodekeys.containsKey(node1)|| !this.nodekeys.containsKey(node2)){
            return false;
        }
        else {
            return (this.edges.get(node1).containsKey(node2));
        }
    }

    /**
     * return the weight of the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if(!this.nodekeys.containsKey(node1)|| !this.nodekeys.containsKey(node2)){
            return -1;
        }
        if(hasEdge(node1, node2))
        {
         return this.edges.get(node1).get(node2); //where to put the weight?
        }
        return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (nodekeys.containsKey(key)){
            //do none, there's already a node with this key
        }
        else{
         //   System.out.println("huh?");
            this.nodeSize= this.nodeSize+1;
        //    System.out.println(this.nodeSize);
            nodekeys.put(key, new NodeInfo(key));
      //      neinodes.put(key, new HashMap<>());
            edges.put(key, new HashMap<>());
            this.addNode(key);
        }
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     *
     * @param node1
     * @param node2
     * @param w
     */

    public void connect(int node1, int node2, double w) {
        if(w<0){
            System.out.println("WARNING. I can't do that. this graph accepts ONLY edges with weight 0 or greater.");
        }
        else {
            if (!nodekeys.isEmpty()) { //can't connect sth in an empty graph
                if (!this.nodekeys.containsKey(node1) || !this.nodekeys.containsKey(node2)) { ///if this node doesnt exist in the graph, you can't connect it to another one
                    // sout("one of the nodes doesn't exist in the graph");
                    // System.out.println(node1+" "+node2);
                    // System.out.println(nodekeys.keySet());

                } else {
                    if (node1 == node2) {
                        //do nothing or throw exception of "you're trying to connect a node to itself"
                        // System.out.println("YO");
                    } else {
                        if (!this.edges.get(node1).containsKey((node2)) && !this.edges.get(node2).containsKey(node1)) {
                            //if there's an already existed edge, just update the weight
                            edges.get(node2).put(node1, w);
                            edges.get(node1).put(node2, w);
                            mc++;
                            this.edgeSize++;
                        } else {
                            edges.get(node2).put(node1, w);
                            edges.get(node1).put(node2, w);
                            mc++;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time.
     * @return Collection<node_info>
     */
    public Collection<node_info> getV(){
        return this.nodekeys.values();
    }

    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id
     * Note: this method should run in O(1) time.
     * @return Collection<node_info>
     */
    public Collection<node_info> getV(int node_id) {
        Collection <node_info> neis = new LinkedList <node_info>();
        int conv=0;
        if (nodekeys.containsKey(node_id)) {
            Set nei= edges.get(node_id).keySet();
            for (Object key: nei) {
            //    System.out.println(key);
            //    conv = (int) key;
                neis.add(nodekeys.get(key));
            //    System.out.println(key);
            }
            return neis;
        }
        else {
            return null;
        }
    }
    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */

    public node_info removeNode(int key) {
        node_info removedata=null;
        if(!nodekeys.containsKey(key)|| nodekeys.get(key)==null){
            // System.out.println("hey edgesize");
            // do nothing, it's not in nodekeys!
            //                    //  throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        else {
            removedata = this.nodekeys.get(key);
            mc++;
            for (node_info nd: this.getV(key)) {
            //    this.neinodes.get(nd.getKey()).remove(key);
                this.edges.get(nd.getKey()).remove(key);
                this.edgeSize = this.edgeSize - 1;
                mc++;
            }
            this.nodekeys.remove(key);
            this.nodeSize--;
        }
        return removedata;
    }
    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
    public void removeEdge(int node1, int node2){
        if (!this.nodekeys.containsKey(node1) || !this.nodekeys.containsKey(node2)) {
            //throw exception: "no key/s like this/these in the graph"
            //    System.out.println("this1");
        }
        else {
            if( !this.edges.get(node1).containsKey(node2)){
                //they're not neighbors so don't do anything!
                //      System.out.println("this2");

            }
            else {
                mc++; //INCREASE BY TWO OR ONE? WE SHALL FIGURE LATER
                mc++;
                //         System.out.println("this3");
             //   this.neinodes.get(node1).remove(node2);
            //    this.neinodes.get(node2).remove(node1);
                this.edges.get(node1).remove(node2);
                this.edges.get(node2).remove(node1);
                this.edgeSize = this.edgeSize - 1;
            }
        }
    }
    /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */

    public int nodeSize(){
        return this.nodeSize;
    }
    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
    public int edgeSize(){
        return this.edgeSize;
    }
    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
    public int getMC(){
        return this.mc;
    }
}