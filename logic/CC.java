package logic;


import java.util.LinkedList;
import constants.Vertex;
import graph.Graph;

// detecting connected components

public class CC {
    boolean marked[];
    int[] id; // holds every vertexs component id
    int count; // the component lengeth
    LinkedList<Integer>[] component; // connected component
    int V;
    Graph D;
    DepthFirstSearch dfs;

    public CC(Graph D) {
        dfs = new DepthFirstSearch(D);
        this.D = D;
        this.V = D.V();
        marked = new boolean[V];
        id = new int[V];
        component = (LinkedList<Integer>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
            component[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < V; i++) {
            if (!marked[i]) {
                dfs(D, i);
                // by completing the recursive calls it means that the DFS traversed through all
                // the connected verteces (not marked) from the i vertex
                count++; // incrementing count means start a new connected component
            }
        }
    }

    void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count; // setting to every vertex it's id (count)
        component[count].add(v); // adding the vertex to it's corresponding component
        int index;
        for (Vertex<Integer> i : G.adj(v)) {
            index = i.getIndex();
            if (!marked[index]) {
                dfs(G, index);
            }
        }
    }

   

    // graph is connected if and only if there is only one connected conponent
    public boolean isConnected() {
        return count == 1;
    }

    public boolean euler() {
        int even = 0; // total even adges of a graph
        int odd = 0; // total odd(unpair) edges of a graph
        int temp; // holds the total degree of a vertex

        for (int i = 0; i < V; i++) {
            // indgree has to equal the outdgree of every vertex of the the digraph
            if (D.getInDgree(i) != D.getOutDgree(i))
                return false;
            temp = D.getVertex(i).totalDgree();
            // counting the even and odd adges
            if (temp % 2 == 0) {
                even++;
            } else
                odd++;
        }

        // a graph either has to have all degrees even or two verteces with odd dgree
        // and the rest with even dgrees
        if (even == V || odd == 2) {
            return true;
        } else
            return false;
    }

    public boolean hami() {
        // if the verteces traversed from the first to the last vertex equals the number
        // of verteces means that it traversed all the edges exactly one time
        if (!dfs.hasPathTo(V-1)) return false;
        if (dfs.pathTo(V - 1).size() == V)
            return true;
        return false;
    }

    // getters
    public int count() {
        return count;
    }

    public LinkedList<Integer> component(int i) {
        return component[i];
    }

    int id(int v) {
        return id[v];
    }
}
