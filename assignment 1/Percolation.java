//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF connectivity; // Associates grid variable with WQUUF object type
    boolean[][] nodes;
    
    public Percolation(int n){
       // Creates an nxn grid
       connectivity = new WeightedQuickUnionUF((int) Math.pow(n, 2)); // new creates the object, constructor initializes the object
       nodes = new boolean[n][n];
       for (int i = 0; i < nodes.length; i++){
           for (int j = 0; i < nodes.length; i++) nodes[i][j] = false;
       }
    }
    
    public void open(int row, int col){
        // Opens site at row, col (1 indexed)
        row -= 1;
        col -= 1;
        nodes[row][col] = true;
        for (int i = row - 1; i <= row + 1; i++){
            for (int j = col - 1; j <= col + 1; j++){
                if (nodes[i][j] == true){
                   int p = nodes.length*row + col;
                   int q = nodes.length*i + j;
                   connectivity.union(p, q);
                }
            }
        }
    }
 /*   
    public boolean isOpen(int row, int col){
        return grid[row][col];
    }
    
    public boolean isFull(int row, int col){
    }// is site (row, col) full? => Open site that can be connecte to an open site in the top row via a chain of neighboring open sites
    
    public int numberOfOpenSites(){
    }// number of open sites
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