package ex1.src;
import ex1.src.node_info;
import ex1.src.weighted_graph;

import java.util.List;
/**
 * This interface represents an Undirected (positive) Weighted Graph Theory algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected();
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<node_data> shortestPath(int src, int dest);
 * 5. Save(file);
 * 6. Load(file);
 *
 * @author boaz.benmoshe
 *
 */
public interface weighted_graph_algorithms {
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    void init(weighted_graph g);

    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    weighted_graph getGraph();
    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
    weighted_graph copy();
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * @return
     */
    boolean isConnected();
    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    double shortestPathDist(int src, int dest);
    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    List<node_info> shortestPath(int src, int dest);

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    boolean save(String file);

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    boolean load(String file);
}