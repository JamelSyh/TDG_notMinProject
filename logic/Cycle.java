package logic;

import java.util.Stack;
import constants.Vertex;
import graph.Graph;

// detecting cycles

public class Cycle {
    boolean[] marked;
    boolean[] onStack; // used to determin wether the garph has cycles or not
    Stack<Integer> diCycle; // used to stack the first cycle of verteces if there are any

    int[] edgeTo; // every index of this array holds its parent vertex
    int V;

    public Cycle(Graph D) {
        V = D.V();
        marked = new boolean[V];
        onStack = new boolean[V];
        edgeTo = new int[V];
        diCycle = new Stack<Integer>();

        // traversing through all the the verteces using Depth first search
        for (int i = 0; i < V; i++) {
            if (!marked[i])
                dfs(D, i, i);

        }
    }

    // v is the parent vertex of the current vertex and the s is the the staring
    // vertex
    void dfs(Graph D, int v, int s) {
        marked[v] = true; // marking the traversed vertex
        onStack[v] = true; // to check if there are repeated verteces
        int index; // holds the index of the traversed vertex
        // traversing through all the the adjacent verteces of the parent vertex
        for (Vertex<Integer> i : D.adj(v)) {
            index = i.getIndex();

            // stop if we already have a cycle
            if (this.hasCycle())
                return;

            // continue traversing through the graph
            else if (!marked[index]) {
                edgeTo[index] = v; // set the parent vertex in the index of it's adjacent vertex
                dfs(D, index, v); // retraversing through the adjacent verteces of the current vertex traversing
                                  // recursively

            }

            // cheking for not directed cycles
            else if (!D.isDirected()) {
                if (v != index) {
                    for (int j = v; j != index; j = edgeTo[j]) {
                        diCycle.push(j);
                    }
                    diCycle.push(index);
                    diCycle.push(v);
                }
            } 
            
            // checking for directed cycles
            else if (onStack[index]) {
                for (int j = v; j != index; j = edgeTo[j]) {
                    diCycle.push(j);
                }
                diCycle.push(index);
                diCycle.push(v);
            }
        }
        // empty the stack
        onStack[v] = false;
    }

    // getters
    public boolean hasCycle() {
        return !diCycle.isEmpty();
    }

    public Stack<Integer> cycle() {
        return diCycle;
    }

    public void show() {
        for (int i = cycle().size() - 1; i >= 0; i--) {
            System.out.print(cycle().elementAt(i) + " ");
        }
        System.out.println();
    }

}
