package constants;

// vertex class

public class Vertex<E> {
    int inDgree; 
    int outDgree;
    int index; // id
    E value; // not used for the moment

    // constructor (construct the vertex obj with a corresponding index and default values) 
    public Vertex(int index) {
        inDgree = 0;
        outDgree = 0;
        this.index = index;
    }
    
    // getters
    public int getIndex() {
        return index;
    }

    public int getOutDgree() {
        return outDgree;
    }

    public int getInDgree() {
        return inDgree;
    }

    public int totalDgree() {
        return inDgree + outDgree;
    }

    // setters
    public void increaseInDgree() {
        inDgree++;
    }

    public void increaseOutDgree() {
        outDgree++;
    }

    
}
