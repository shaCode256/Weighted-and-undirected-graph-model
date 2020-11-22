package ex1.src;

public interface node_info {
    /**
     * Return the key (id) associated with this node.
     * Note: each node_data should have a unique key.
     * @return
     */
    int getKey();

    /**
     * return the remark (meta data) associated with this node.
     * @return
     */
    String getInfo();
    /**
     * Allows changing the remark (meta data) associated with this node.
     * @param s
     */
    void setInfo(String s);
    /**
     * Temporal data (aka distance, color, or state)
     * which can be used be algorithms
     * @return
     */
    double getTag();
    /**
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * @param t - the new value of the tag
     */
    void setTag(double t);
}
