package graph;

import java.util.LinkedList;
import constants.Vertex;

// main graph class

public class Graph {
    LinkedList<Vertex<Integer>>[] adj; // array of a list of adjacent verteces
    Vertex<Integer>[] verteces; // array of verteces
    Vertex<Integer>[] reverseGraphVerteces; // array of verteces for the reversed graph
    boolean[] marked; // arary of marked verteces (used for traversing the graph)
    int V;
    int E;
    boolean directed;

    // second constructor
    Graph(int V) {

        // initializing variables
        this.E = 0;
        this.V = V;

        adj = (LinkedList<Vertex<Integer>>[]) new LinkedList[V];
        verteces = (Vertex<Integer>[]) new Vertex[V];
        marked = new boolean[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Vertex<Integer>>();
            verteces[i] = new Vertex<Integer>(i);
        }
    }

    // first constructor
    public Graph(InputSystem inSys) {
        this(inSys.V()); // sending V to the second construcor
        // initializing
        this.E = inSys.E();
        directed = inSys.isDirected();

        // traversing through the asociate matrix to add adges (by adding a vertex to
        // the corresponding index of the adj array)
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // if there is parallel edges (parallel edges and loops are supported)
                for (int k = 0; k < inSys.aso(i, j); k++) {
                    // initializing an object from vertex class with index
                    Vertex<Integer> vertex = new Vertex<Integer>(j);
                    // adding an adge from i to the added vertex
                    addEdge(i, vertex);

                }
            }
        }
    }

    void addEdge(int v, Vertex<Integer> vertex) {

        adj[v].add(vertex); // adding the vertex to the list of the index corresponding to parent vertex in
                            // the adj array
        verteces[v].increaseOutDgree(); // incrementing the out dgree of the parent vertex by 1
        verteces[vertex.getIndex()].increaseInDgree(); // incrementing the in dgree of the added vertex by 1
    }

    // printing method
    public void show() {
        int counter = 0;
        for (LinkedList<Vertex<Integer>> item : adj) {
            System.out.print(counter + " : ");

            for (Vertex<Integer> i : item) {
                System.out.print(i.getIndex() + " - ");
            }
            counter++;
            System.out.println();
        }
    }

    // reversing graph mothod by creating a new graph obj and revering the edges
    public Graph reverse() {
        Graph reverseG = new Graph(V);
        reverseGraphVerteces = (Vertex<Integer>[]) new Vertex[V];

        for (int i = 0; i < V; i++) {
            reverseGraphVerteces[i] = new Vertex<Integer>(i);
            for (Vertex<Integer> j : adj[i]) {
                Vertex<Integer> vertex = new Vertex<Integer>(i);
                reverseG.addEdge(j.getIndex(), vertex);
            }
        }
        return reverseG;
    }

    // getters
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public LinkedList<Vertex<Integer>> adj(int v) {
        return adj[v];
    }

    public boolean isDirected() {
        return directed;
    }

    public int getInDgree(int index) {
        return verteces[index].getInDgree();
    }

    public int getOutDgree(int index) {
        return verteces[index].getOutDgree();
    }

    public Vertex<Integer> getVertex(int index) {
        return verteces[index];
    }

}
