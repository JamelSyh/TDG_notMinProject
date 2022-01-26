package graph;

import java.util.Stack;
import logic.DepthFirstSearch;
import logic.Cycle;


// helper class

public class TopologicalSort {
    DepthFirstSearch DAG;// using the DigraphDFS class to order the graph

    public TopologicalSort(Graph D, Cycle C) {
        // garph should not be cyclic
        if (C.hasCycle()) {
            return;
        }
        else
            DAG = new DepthFirstSearch(D); // DigraphDFS object
    }

    
    // printing the sorted graph (reverse Post Order)
    public void showSorted() {
        for (int i = this.Sorted().size() - 1; i >= 0; i--) {
            System.out.print(this.Sorted().elementAt(i) + " --> ");
        }   
    }

    // getter
    Stack<Integer> Sorted() {
        return DAG.reversePost();
    }
}
