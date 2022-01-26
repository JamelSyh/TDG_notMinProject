package logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import constants.Vertex;
import graph.Graph;

// BreadthFirstSearch algorithm to traverse through the graph with the shottest path possible

public class BreadthFirstSearch {
    boolean[] marked;
    int[] edgeTo;
    int s;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    // bfs works by enQueueing the parent vertex then deQueueing it before starting
    // to enQueueing its adjacent verteces
    void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<Integer>(); // initialize the queue
        marked[s] = true;
        int index;
        queue.add(s); // enQueueing the parent vertex

        while (!queue.isEmpty()) {
            int v = queue.remove(); // deQueueing the parent vertex
            // interate through the adjacent verteces of the parent vertex and inqueueing them
            for (Vertex<Integer> i : G.adj(v)) {
                index = i.getIndex();
                if (!marked[index]) {
                    marked[index] = true;
                    edgeTo[index] = v;
                    queue.add(index); // enQueueing the vertex
                }
            }
        }
    }

    // same path logic of DFS

    public boolean hasPathTo(int v) {
        return marked[v];
    }

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
}
