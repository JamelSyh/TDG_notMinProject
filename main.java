import graph.Graph;
import graph.InputSystem;
import graph.OutputSystem;


class Main {
    public static void main(String[] args) {
        
        InputSystem inSys = new InputSystem(); //init inSys object form InputSystem class
        Graph D = new Graph(inSys); // init D object form Digraph class
        new OutputSystem(D); // init outSys object form OutputSystem class
        
    }
}