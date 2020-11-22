package ex1.src;

//import ex0.node_info;

//import ex1.node_info;

import ex1.src.node_info;

import java.io.Serializable;

public class NodeInfo
        implements node_info, Serializable {

    private int key=0;
    public static int count=0;
    private String info;
    private double tag;
    private int parent;
    private static final int copykey=-1;

    //Has HashMap with this node's neighbors keys AS KEYS
    // and hashmaps AS VALUES
    // containing node_infos of the neighbors as the KEYS and double nums as the edge's size
    //between them and the node as the VALUES!

   // private HashMap<Integer, node_info> neighbors= new HashMap<Integer,node_info>();
    //public static void putKey (int keys){ //to initialize the keys count when we create a new graph
    //    NodeInfo.count =keys;
    //} //don't really need this anymore since while creating anode we specify a key for it,
    //in this project
    //public static void addNi (node_info n){ //to initialize the keys count when we create a new graph
//        this.neighbors.put(n,et);
//    }


    public NodeInfo(int key) {
        count=count;
        this.key=key;// Increasing to keep the individuallity of the ids //was count++;
        int tag=0;
        String info="";
        //this.neighbors= new HashMap<Integer,node_info>();
    }
    /**
     * This interface represents the set of operations applicable on a
     * node (vertex) in an (undirectional) unweighted graph.
     * @author boaz.benmoshe
     *
     */
    /**
     * Return the key (id) associated with this node.
     * @return
     */

    public int getKey() {
        return this.key;
    }

    /**
     * return true iff this<==>key are adjacent</==>
     * @param key
     * @return
     */
//    public boolean hasNi(int key) {
//        if(this.neighbors.containsKey(key)){
//            return true;
//        }
//        return false;
//    }

//    /** This method adds the node_info (t) to this node_info.*/
//    public void addNi(node_info t) {
//        if(t.getKey()==this.key|| this.neighbors.containsKey(t.getKey())){
//            //do nothing, you guys have the same key.
//        }
//        else {
//            this.neighbors.put(t.getKey(), t);
//        }
//    }
//
//    /**
//     * Removes the edge this-key
//     //   param key
//     */
//    public void removeNode(node_info node) {
//        if (!this.neighbors.containsKey(node.getKey())) {
//            System.out.println("there's no node with this key in this graph");
//        } else {
//            this.neighbors.remove(node.getKey()); //remove this node from the neighbors list of the node
//        }
//
//    }
    /**
     * return the remark (meta data) associated with this node.
     * @return
     */
    public String getInfo() {
        return this.info;
    } ////key of father
    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    public void setInfo(String s) {
        this.info=s;
    } //info is the color is this graph.
    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     * @return
     */
    public double getTag() {
        return this.tag;
    } //distance of weight
    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    public void setTag(double t) {
        this.tag=t;
    } //the distance

}