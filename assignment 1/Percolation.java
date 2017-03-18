//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF connectivity; // Associates grid variable with WQUUF object type
    boolean[][] nodes;
    int n_conn;
    
    public Percolation(int n){
       // Creates an nxn grid
       /* To-do:
        * > Throw IllegalArgumentException if n<=0
        */
       n_conn = n + 2;
       connectivity = new WeightedQuickUnionUF((int) Math.pow(n, 2) + 2); // new creates the object, constructor initializes the object
       nodes = new boolean[n][n];
       for (int i = 0; i < nodes.length; i++){
           for (int j = 0; i < nodes.length; i++) nodes[i][j] = false;
       }
    }
    
    public void open(int row, int col){
        // Opens site at row, col (1 indexed)
        /* To-do:
         * > Throw exception if index is out of bounds
         */
        int p = (nodes.length - 1)*row + col; // posn. in connectvty.
        row -= 1;
        col -= 1;
        nodes[row][col] = true;
        for (int i = row - 1; i <= row + 1; i++){
            for (int j = col - 1; j <= col + 1; j++){
                if (nodes[i][j] == true){
                   int q = nodes.length*i + j + 1;
                   connectivity.union(p, q);
                }
            }
        }
        if (row == 0) connectivity.union(0, p);
        if (row == nodes.length) connectivity.union(n_conn, p);
    }

    public boolean isOpen(int row, int col){
        /* To-do:
         * - Throw IndexOutofBoundsException if outside range
         */
        return nodes[row-1][col-1];
    }

    public boolean isFull(int row, int col){
        int p = (nodes.length - 1)*row + col;
        return connectivity.connected(0, p);
    }// is site (row, col) full? => Open site that can be connected to an open site in the top row via a chain of neighboring open sites

    public int numberOfOpenSites(){
        int n_open = 0;
        for (int i = 1; i <= nodes.length + 1; i++){
            for (int j = 1; j <= nodes.length + 1; j++){
                if (nodes[i][j]) n_open += 1;
            }
        }
        return nodes
    }
    
    /*
    public boolean percolates(){
    } //  does the system percolate?
        
    public static void main(String[] args){} // optional test client
    */
}

/* Corner cases: 
 * - Row and column indices are between 1 and n
 * - (1, 1) is upper-left site
 * - Throw java.lang.IndexOutOfBoundsException if any argument to isOpen() or isFull() is outside its range
 * - Throw java.lang.IllegalArgumentException if n <= 0
 */