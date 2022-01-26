package graph;

import graph.*;
import logic.BreadthFirstSearch;
import logic.CC;
import logic.Cycle;
import logic.SCC;

// class responsible for the output

public class OutputSystem {
    // using the following classes
    Graph D;
    Cycle cycle;
    CC connected;
    SCC Sconnected;
    TopologicalSort sort;
    BreadthFirstSearch bfs;

    public OutputSystem(Graph D) {
        // initializing
        this.D = D;
        cycle = new Cycle(D);
        connected = new CC(D);
        Sconnected = new SCC(D);
        sort = new TopologicalSort(D, cycle);

        // prinitng the processed information about the given graph
        if (D.isDirected()) {
            System.out.println("Directed Graph : ");
            graph();
            connectivity();
            strongConnectivity();
            euler();
            hami();

            if (cycle.hasCycle()) {
                System.out.println("cyclic DiGraph.");
                diCycle();

            } else {
                System.out.println("Acyclic DiGraph.");
                sorted();
            }

        } else {
            System.out.println("Not Directed Graph : ");
            graph();
            connectivity();
            euler();
            hami();

            if (cycle.hasCycle()) {
                System.out.println("cyclic Graph.");

                cycle();
            } else {
                System.out.println("Acyclic Graph.");
                sorted();
            }

        }
        shorestPath();

    }

    // abstraction

    void graph() {
        D.show();
        System.out.println();
    }

    void connectivity() {
        System.out.println("connected : " + connected.isConnected());
        System.out.println("conncted component : ");
        for (int i = 0; i < connected.count(); i++) {
            for (int j : connected.component(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void strongConnectivity() {
        System.out.println("Strongly connected : " + Sconnected.isSC());
        System.out.println("Strongly connected component : ");
        Sconnected.show();
    }

    void cycle() {
        System.out.print("first cycle component : ");

        cycle.show();
        System.out.println();
    }

    void diCycle() {

        System.out.print("first diCycle component : ");
        cycle.show();
        System.out.println();
    }

    void euler() {
        System.out.println("Eulerian : " + connected.euler());

    }

    void hami() {
        System.out.println("Hamiltonian : " + connected.hami());
    }

    void sorted() {
        System.out.println("sorted graph : ");
        sort.showSorted();
        System.out.println();
    }

    void shorestPath() {
        int temp;
        System.out.println("Shortest Possible Paths : ");
        for (int i = 0; i < D.V(); i++) {
            bfs = new BreadthFirstSearch(D, i);

            for (int j = 0; j < D.V(); j++) {

                System.out.print("from " + i + " to " + j + " : ");
                if (bfs.hasPathTo(j)) {
                    temp = bfs.pathTo(j).size() - 1;
                    for (int k = temp; k >= 0; k--) {

                        System.out.print(bfs.pathTo(j).elementAt(k) + " --> ");

                    }
                } else
                    System.out.print("non");
                System.out.println();
            }
        }
    }
}
