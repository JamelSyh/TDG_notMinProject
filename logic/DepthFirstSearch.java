package logic;

import java.util.Stack;
import constants.Vertex;
import graph.Graph;


// DepthFirstSearch algorithm to recursivly traverse through the graph

public class DepthFirstSearch {
    boolean[] marked;
    int[] edgeTo;
    int s;
    int count;
    int[] id;
    Stack<Integer> reversePostOrder; // holds the post reverse ordered graph

    public DepthFirstSearch(Graph D) {
        count = 0;
        marked = new boolean[D.V()];
        edgeTo = new int[D.V()];
        id = new int[D.V()];
        reversePostOrder = new Stack<Integer>();
        // DFS traversing
        for (int i = 0; i < D.V(); i++) {
            if (!marked[i]) {
                dfs(D, i);
                count++;
            }
        }

    }

    void dfs(Graph D, int v) {
        marked[v] = true;
        id[v] = count;
        int index;
        for (Vertex<Integer> i : D.adj(v)) {
            index = i.getIndex();
            if (!marked[index]) {
                edgeTo[index] = v;
                dfs(D, index);

            }
        }
        // pushing the vertex after done every recursivly traversed vertex
        reversePostOrder.push(v);
    }

    // returns if there is a path to the given vertex v (form the starting point)
    public boolean hasPathTo(int v) {
        return id[0] == id[v];
    }


    // returns the path of verteces that recursivly traversed in DFS using the parent array (edgeTo)
    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    // getters
    public Stack<Integer> reversePost() {
        return reversePostOrder;
    }
}
