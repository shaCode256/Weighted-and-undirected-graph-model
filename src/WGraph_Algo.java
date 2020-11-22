package ex1.src;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class WGraph_Algo
        implements weighted_graph_algorithms {
        private Object node_info;

        public WGraph_Algo() {

        }
        /**
         * This interface represents the "regular" Graph Theory algorithms including:
         * 0. clone();
         * 1. init(String file_name);
         * 2. save(String file_name);
         * 3. isConnected();
         * 5. int shortestPathDist(int src, int dest);
         * 6. List<Node> shortestPath(int src, int dest);
         * 7. load(String file_name);
         * @author boaz.benmoshe
         *
         */
        /**
         * Init this set of algorithms on the parameter - graph.
         * @param g
         */

        private weighted_graph myGraph;
        private WGraph_DS aGraph;

        public void init(weighted_graph g) { //I DONT GET WHAT IS THIS
                this.myGraph=g;
        }

        /**
         * Return the underlying graph of which this class works.
         *
         * @return
         */
        @Override
        public weighted_graph getGraph() {
                return this.myGraph;
        }

        /**
         * Compute a deep copy of this graph.
         * @return
         */

        public weighted_graph copy() {

                WGraph_DS g = new WGraph_DS(); //creating a new graph  --DOES DEEP COPY WITH DIFFERENT KEYS!...
                Collection<ex1.src.node_info> nds = myGraph.getV(); //getting the collection of vertices (nodes)
                //  System.out.println("nds size is"+nds.size());
                int keynode;
                node_info a;
                int akey;
                Iterator it = nds.iterator();//to iterate on the collection!
                while(it.hasNext()) { //keep going on all the collection, copy all the nodes!
                        a = (node_info)it.next();
                        akey= a.getKey(); //get the key of the want-to-be-copied-node
                        //NodeInfo.putKey(akey); //and put it as the new one key
                        g.addNode(akey); //create a new node
                        if (g.getNode(akey) != null) {
//                                System.out.println("hey");
                                g.getNode(akey).setInfo(a.getInfo());
                                g.getNode(akey).setTag(a.getTag());
                        } else {
                                //do none
                        }
                }
//                System.out.println(g.getV());
                node_info sa;
                Iterator its = nds.iterator();//to iterate on the collection!
//                System.out.println(g.getV());
                for (node_info nod : nds) { //for every node- add the neighbors of each node to all the nodes is the graph
                        keynode = nod.getKey();
                        its = g.getV(nod.getKey()).iterator();//to iterate on the collection!
                        while(its.hasNext()) {
                                a = (node_info)its.next();
                                //     for (node_info nei : g.getV(nod.getKey())) { //add to node's neighbores list and connect 'em.
                                g.getV(nod.getKey()).add(a);
                                g.connect(nod.getKey(), a.getKey(), getGraph().getEdge(nod.getKey(), a.getKey()));
                        }
                }
                //System.out.println("g size is"+g.nodeSize());

                return g;
        }

        /**
         * Returns true if and only if (iff) there is a valid path from EVREY node to each
         * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
         * @return
         */

        //BFS IMPLEMENTARION OF PSUDO-CODES LIKE THOSE ON WIKIPEDIA PAGE
        //https://en.wikipedia.org/wiki/Breadth-first_search
        // used the psudo codes- https://www.codesdope.com/course/algorithms-bfs/

        public boolean isConnected() {
                // System.out.println("stuck");
                if (this.myGraph==null){
                        return true;
                }
                Collection c=myGraph.getV();
                if (c.size() == 0 || c.size() == 1) {
                        return true; //Boaz said an empty graph is considered connected, and one node is connected to itself.
                }
                ArrayList<node_info> list = new ArrayList<>(c);
                Map<Integer, node_info> nodekeys = new HashMap<Integer, node_info>();
                for (node_info i : list) nodekeys.put(i.getKey(),i); //putting the collection in a hashmap
                int first= list.get(0).getKey();
                if (myGraph==null|| myGraph.getV()==null|| myGraph.getV(first)==null||!myGraph.getV(first).stream().findFirst().isPresent()) {
                        //if there's no neighbor to the node than it's not a connected graph by definition,
                        // as the graph size is bigger than 1.
                        System.out.println(myGraph.getV(first)==null);
                        return false;
                }
                else{ //it does have a neighbor node. //perform a nodified BFS! of the ver. below.
                        int src=first;
                        int value = myGraph.getV(first).stream().findFirst().get().getKey(); //finding a key in that random hashmap of neighbors
                        int dest= value;
                        //  List<node_info> myListg = new ArrayList<node_info>(); //create a list for returning the shortest path.
                        // List<Integer> visited = new ArrayList<Integer>(); //create a list for returning the shortest path.
                        // for (node_info i : list) nodekeys.put(i.getKey(),i); //for every key in the nodes list put it in the hashmap with its key.
                        //     int desty= dest; //for further use
                        //     int k= Collections.max(nodekeys.keySet())+1; ///gets the biggest key value, to initialize a big enough array
                        //to face problems with keying as for the array of parents, every index is the key of node.
                        for (Integer key : nodekeys.keySet()) { //initializing
                                nodekeys.get(key).setInfo("white"); //color
                                // null because initially, we don't have any path to reach
                        }
                        nodekeys.get(src).setInfo("gray");
                        nodekeys.get(src).setTag(0);// the distance of the source from itself is 0
                        Deque<Integer> q = new ArrayDeque();
                        q.add(src);
                        int count=0;
                        int u;
                        int nop = 0;
                        while (!q.isEmpty()) {
                                u = q.poll();
                                for (node_info i : myGraph.getV(u)) {
                                        if(nodekeys.containsKey((i).getKey())) {
                                                if (nodekeys.get(i.getKey()).getInfo().equals("white")) { // if this node wasn't visited yet
                                                        nodekeys.get(i.getKey()).setInfo("gray"); // mark it as visited
                                                        q.add(i.getKey()); //put this node in the deque (queue with double inserting options, for further uses if needed)
                                                }
                                        }
                                }// the neighbors of this node are discovered
                                //  nodekeys.get(u).setInfo("Black"); // we can mark it as visited.. not needed for this operation right now. keeping this for may-be later uses
                                //  for now we're using this to see all the nodes visited in an arraylist:
                                //visited.add(nodekeys.get(u).getKey());
                                //using count for complexity
                                count++;

                        }
                        //if we visited all nodes than it's a connected graph,
                        // each node is connected to every node.
                        return count == nodekeys.size();//there are unvisited nodes after doing the BFS! so not everyone's connected to everyone!
                }
        }

        /**
         * returns the length of the shortest path between src to dest
         * @param src - start node
         * @param dest - end (target) node
         * @return
         */


        /**
         * returns the shortest path between src to dest - as an ordered List of nodes:
         * src--> n1-->n2-->...dest
         * see: https://en.wikipedia.org/wiki/Shortest_path_problem
         * @param src - start node
         * @param dest - end (target) node
         * @return
         */
        public double shortestPathDist(int src, int dest){
                if(src==dest){ //the distance from a node to itself is 0.
                        return 0;
                }
                List<node_info> aj=shortestPath(src,dest);
                //  System.out.println(aj.size());
                if(aj.isEmpty()){
                        return -1; //EMPTY aj MEANS THERE'S NO PATH FROM SRC TO DEST
                }
                double weight =aj.get(aj.size()-1).getTag();
                return weight; //  with -1 cuz of the edges num, if there are 2 nodes, theres only one edge connecting them
        }
        //I used Dijkstra Algorithm: Short terms and Pseudocode: http://www.gitta.info/Accessibiliti/en/html/Dijkstra_learningObject1.html
        public List<node_info> shortestPath(int src, int dest) { // by Dijkstra's method
                Collection c=myGraph.getV(); //get and store the graphs' collection of vertices.
                Map<Integer, Integer> parents = new HashMap<Integer, Integer>(); //for (space I guess.. wanted to achieve time) complexity reason-
                //a hashmap of parents, to restore the parent of each node in the shortest path
                List<node_info> myListg = new ArrayList<node_info>(); //create a list for returning the shortest path.
                ArrayList<node_info> list = new ArrayList<>(c); //put the nodes in an arraylist.
                Map<Integer, node_info> nodekeys = new HashMap<Integer, node_info>(); //create a new nodes hashmap for the actions required.
                for (node_info i : list) nodekeys.put(i.getKey(),i); //for every key in the nodes list put it in the hashmap with its key.
                int desty= dest; //for further use
                if (dest== src) { //the path of node from itself to itself is itself.
                        myListg.add(nodekeys.get(src));
                        return myListg;
                }
                if (!nodekeys.containsKey(src)||!nodekeys.containsKey(dest)){ //if one of them doesn't exist there's no path
                        return myListg;
                }
                if(myGraph.getV(src).contains(dest)){ //if they're neighbors so this is the shortest path
                        //   System.out.println("here?");
                        myListg.add(nodekeys.get(src));
                        myListg.add(nodekeys.get(dest));
                        return myListg;
                }
                //int k= Collections.max(nodekeys.keySet())+1; ///gets the biggest key value, to initialize a big enough array (I don't use array anymore)
                //to face problems with keying as for the array of parents, every index is the key of node.
                // node_info[] parents = new node_info[k];
                //how to get infinity -https://stackoverflow.com/questions/12952024/how-to-implement-infinity-in-java
                for (Integer key : nodekeys.keySet()) { //initializing- prepearing the nodes for the proccess.
                        nodekeys.get(key).setInfo(""); //previous node, that from it we got to this node,
                        // in the shortest path: is an empty string because we didn't start the paving yet
                        nodekeys.get(key).setTag(Double.POSITIVE_INFINITY); //Shortest distance- we set it to infinity cuz we didn't check it yet
                        parents.put(key, null);                       // null because initially, we don't have any path to reach
                }
                nodekeys.get(src).setTag(0); // distance a node to itelf is 0, and it's the distance of src from itself
                Deque<node_info> q = new ArrayDeque();
                for (Integer key:nodekeys.keySet()) { // all nodes in the graph are unoptimized - thus are in Q
                        q.add(nodekeys.get(key));
                }
                double minDist=Double.POSITIVE_INFINITY;
                int minKey=-1;
                double dist=0;
                node_info minNode=null;
                while (!q.isEmpty()) { //	// main loop
                        //System.out.println("does it contain key"+q.contains(nodekeys.get(src)));
                        for (node_info node:q) { //getting the smallest dist node
                                // System.out.println("key is"+node.getKey());
                                if(node.getTag()<=minDist){
                                        minDist=node.getTag();
                                        minKey=node.getKey();
                                        minNode=node;
                                }
                        }
                        q.remove(minNode);
                        for (node_info neis: myGraph.getV(minKey)) {  //where v has not yet been removed from Q.
                                // System.out.println("heya");
                                if(q.contains(neis)){ //where v has not yet been removed from Q.
                                        //   if(getGraph().getEdge(neis.getKey(), minKey)!=-1) { should be^^ for
                                        dist = minDist + getGraph().getEdge(neis.getKey(), minKey); //alt := dist[u] + dist_between(u, v)
                                        if (dist < neis.getTag()) {
                                                neis.setTag(dist);
                                                neis.setInfo(String.valueOf(minKey));
                                        }
                                        //   }
                                }
                        }
                        minDist=Double.POSITIVE_INFINITY;
                } //done with while loop.
                desty=dest;
                while(nodekeys.get(desty).getTag()!=0&&nodekeys.get(desty).getInfo()!=""){ //means you've reached the src because only the src has dist(TAG)
                        //of 0 from itself.
                        //        System.out.println(dest);
                        myListg.add(nodekeys.get(desty));
                        desty = Integer.parseInt(nodekeys.get(desty).getInfo());
                }
                myListg.add(nodekeys.get(src));
//                for (int i=0; i<myListg.size(); i++) {
//                        System.out.println(myListg.get(i).getKey());
//                }
                Collections.reverse(myListg); //reverse the list, 'cuz it came reversed, as we got from the dest to src by parents.
                //   System.out.println("myListg.size ISSSSS"+myListg.size());
                if(myListg.get(myListg.size()-1).getKey()!=dest){
                        //  System.out.println("YES");
                        myListg.clear();
                }
                return myListg;
        }

        /**
         * Saves this weighted (undirected) graph to the given
         * file name
         *
         * @param file - the file name (may include a relative path).
         * @return true - iff the file was successfully saved
         */
        @Override

        //how to save an object to a file
        //https://examples.javacodegeeks.com/core-java/io/fileoutputstream/how-to-write-an-object-to-file-in-java/
        //"file"- path for example- private static final String filepath="C:\\Users\\nikos7\\Desktop\\obj";
        //if it's in the same directiory of the project, it just needs name.

        //Using Serialization
        public boolean save(String file) {
                try {
                        // Saving of object in a file
                        FileOutputStream filey = new FileOutputStream(file);
                        ObjectOutputStream out = new ObjectOutputStream(filey);

                        // Method for serialization of object
                        out.writeObject(this.myGraph);

                        out.close();
                        filey.close();
                } catch (IOException ex) {
                        System.out.println("save-IOException is caught");
                        return false;
                }
                return true;
        }

        /**
         * This method load a graph to this graph algorithm.
         * if the file was successfully loaded - the underlying graph
         * of this class will be changed (to the loaded one), in case the
         * graph was not loaded the original graph should remain "as is".
         *
         * @param file - file name
         * @return true - iff the graph was successfully loaded.
         */
            @Override
        //how to load an object from a file
        //Using Serialization
        public boolean load(String file) {
                try{
                        // Reading the object from a file
                        FileInputStream filey = new FileInputStream(file);
                        ObjectInputStream in = new ObjectInputStream(filey);
                        // Method for deserialization of object
                        WGraph_DS a = (WGraph_DS) in.readObject();
                        in.close();
                        // System.out.println("z = " + object1.z);
                        this.myGraph=a;
                }

                catch (IOException ex) {
                        System.out.println("load-IOException is caught");
                        return false;
                }

                catch (ClassNotFoundException ex) {
                        System.out.println("ClassNotFoundException" +
                                " is caught");
                        return false;
                }
                return true;
        }


} // #last_one_standing