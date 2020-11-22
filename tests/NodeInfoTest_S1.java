package ex1.tests;

import ex1.src.NodeInfo;
import ex1.src.node_info;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//https://www.vogella.com/tutorials/JUnit/article.html

class NodeInfoTest_S1 {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    List<node_info> nodes= new ArrayList<>();
    long start = new Date().getTime();


    @BeforeEach //Executed before each test. It is used to prepare the test environment
        // (e.g., read input data, initialize the class).

    void setUp() {
        start = new Date().getTime();
        System.out.println(ANSI_GREEN+"Setting up the environment. creating the nodes..."+ANSI_RESET);
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

//        public list<node_info> getNodes(){
//            return nodes;
//        }
    }

    @AfterEach //Executed after each test. It is used to cleanup the test environment
        // (e.g., delete temporary data, restore defaults). It can also save memory by cleaning up
        // expensive memory structures.

    void tearDown() {
        long end = new Date().getTime();
        double dt = (end-start)/1000.0;
        System.out.println("This test took "+dt+" seconds");

    }

    @Test
    void getKey() {
        System.out.println(ANSI_GREEN+"Checking if keys of nodes are ok..."+ANSI_RESET);
        for(int i=0; i<20; i++) {
            assertEquals(nodes.get(i).getKey(), i);
        }
    }

    @Test
    void getInfo() {
        System.out.println(ANSI_GREEN+"Checking if getting infos of nodes is ok..."+ANSI_RESET);
        Integer obj=0;
        String si=null;
        String strcomp=null;
        for(int i=0; i<20; i++) {
            obj = i;
            si = obj.toString();
            strcomp=si+"+0.5";
            assertEquals(nodes.get(i).getInfo(), strcomp);
        }

    }

    @Test
    void setInfo() {
        System.out.println(ANSI_GREEN+"Checking if setting infos of nodes is ok..."+ANSI_RESET);
        Integer obj=0;
        String si=null;
        String strcomp=null;
        for(int i=0; i<20; i++) {
            obj = i;
            si = obj.toString();
            strcomp=si+"+0.5";
            nodes.get(i).setInfo(strcomp);
            assertEquals(nodes.get(i).getInfo(), strcomp);
        }
    }

    @Test
    void getTag() { //check the get tags, than change some nodes tags, to see if it messes up anything.
        System.out.println(ANSI_GREEN+"Checking if getting tags of nodes is ok..."+ANSI_RESET);
        double di;
        for(int i=0; i<20; i++) {
            di= i;
            assertEquals(nodes.get(i).getTag(),di+0.5);
        }
//        nodes.remove(3);
//        nodes.remove(5);
//        nodes.remove(7);
//        nodes.remove(10);
        //Removes the element at the specified position in this list (optional operation).
        // Shifts any subsequent elements to the left (subtracts one from their indices).
        // Returns the element that was removed from the list.-- messes up our test,
        // according to this list data structure feature. so we won't use it.
        //instead, we'll try to change some nodes tags, to see it THIS messes up sth, as THIS is a problem.
        nodes.get(3).setTag(300);
        nodes.get(5).setTag(301);
        nodes.get(7).setTag(302);
        nodes.get(10).setTag(303);

        for(int i=0; i<20; i++) {
      //      System.out.println(nodes.get(i).getKey());
            if( i!=3&& i!=5&& i!=7&& i!=10) {
                di = i;
                assertEquals(nodes.get(i).getTag(), di + 0.5);
            }
        }
    }

    @Test
    void setTag() {
        System.out.println(ANSI_GREEN+"Checking if setting tags of nodes is ok..."+ANSI_RESET);
        double di;
        for(int i=0; i<20; i++) {
            di= i;
            nodes.get(i).setTag(di+0.5);
        }
    }
}