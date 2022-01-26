package logic;

import java.util.LinkedList;
import constants.Vertex;
import graph.Graph;

// detecting strongly connected components

public class SCC {
    boolean[] marked;
    int[] id;
    public LinkedList<Integer>[] component; // strongly connected components
    int count;


    // using Kosaraju's algorithm for strong connectivity

    public SCC(Graph D) {
        marked = new boolean[D.V()];
        id = new int[D.V()];
        component = (LinkedList<Integer>[]) new LinkedList[D.V()];
        for (int i = 0; i < D.V(); i++) {
            component[i] = new LinkedList<Integer>();
        }
        DepthFirstSearch order = new DepthFirstSearch(D.reverse()); // traversing through the reversed graph and getting its
                                                        // reversePostOrder

        // traversing through the reversedPost stack and adding the connected components
        // which in this case (reverse garph and postrevers Order) are Strongly
        // Connected
        // with the same logic of connected components
        for (int i : order.reversePost()) {
            if (!marked[i]) {
                dfs(D.reverse(), i);
                count++;
            }
        }

    }

    void dfs(Graph D, int v) {
        marked[v] = true;
        id[v] = count;
        component[count].add(v);

        for (Vertex<Integer> i : D.adj(v)) {
            if (!marked[i.getIndex()]) {
                dfs(D, i.getIndex());
            }
        }
    }

    public void show() {
        for (int i = 0; i < count; i++) {
            for (int j : component[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    int count() {
        return count;
    }

    // digraph is Strongly connected if and only if there is one strong connected component
    public boolean isSC() {
        if (count == 1)
            return true;
        else
            return false;
    }
}
