package graph;

import java.util.Scanner;

// class responsible for the intput 

public class InputSystem {
    int V; // number of Verteces
    int E; // number of Edges
    int adj[][]; // adjacency matrice
    int aso[][]; // asociate matrice
    int inc[][]; // incidence matrice
    boolean isdirected; // has the graph directed edges;
    Scanner in; // in object from Scanner class to get input from the user

    public InputSystem() {
        isdirected = false; // setting it to false initially
        in = new Scanner(System.in);  // initializing the in obj
        V = input("Enter V : "); // get V form the user
        E = input("Enter E : "); // ger E form the user

        //initializing the matrices dynamicly
        adj = new int[V][V];
        aso = new int[V][V];
        inc = new int[V][E];

        System.out.println("enter associate matrice : ");
        matInput(V, V, aso);
        System.out.println("enter incidence matrice : ");
        matInput(V, E, inc);

    }

    // abstracting the input mothode
    int input(String text) {
        System.out.print(text);
        return in.nextInt();
    }

    // get mat from the user
    void matInput(int V, int E, int[][] mat) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < E; j++) {
                mat[i][j] = input("Enter val of [" + i + "][" + j + "] : ");
                // if one value at least lower than 0 means that the graph is directed (form inc matrix)
                if (mat[i][j] < 0 && !isdirected)
                    this.isdirected = true;
            }
            System.out.println();
        }
    }

    //getters 
    
    public int adj(int v, int e) {
        return adj[v][e];
    }
    
    public int aso(int v, int e) {
        return aso[v][e];
    }
    
    public int inc(int v, int e) {
        return inc[v][e];
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    public boolean isDirected() {
        return isdirected;
    }
}
