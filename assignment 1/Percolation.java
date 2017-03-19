//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Random;

public class Percolation {
    public WeightedQuickUnionUF connectivity; // Associates grid variable with WQUUF object type
    public boolean[][] nodes;
    public int n_conn;
    
    public Percolation(int n){
       // Creates an nxn grid
       if (n <= 0) throw new IllegalArgumentException();
       n_conn = (int) Math.pow(n, 2) + 2;
       connectivity = new WeightedQuickUnionUF(n_conn); // new creates the object, constructor initializes the object
       nodes = new boolean[n][n];
       for (int i = 0; i < nodes.length; i++){
           for (int j = 0; i < nodes.length; i++) nodes[i][j] = false;
       }
    }
    
    public void open(int row, int col){
        // Opens site at row, col (1 indexed)
        if (row*col == 0 || row > nodes.length || col > nodes.length)
            throw new IndexOutOfBoundsException();
        int p = (row - 1)*nodes.length + col; // posn. in connectvty.
        row -= 1;
        col -= 1;
        nodes[row][col] = true;
        for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, nodes.length - 1); i++){
            if (nodes[i][col]){
               int q = nodes.length*i + col + 1;
               connectivity.union(p, q);
            }
        }
        for (int j = Math.max(col - 1, 0); j <= Math.min(col + 1, nodes.length - 1); j++){
            if (nodes[row][j]){
               int q = nodes.length*row + j + 1;
               connectivity.union(p, q);
            }
        }
        if (row == 0) connectivity.union(0, p);
        if (row == nodes.length - 1) connectivity.union(n_conn - 1, p);
    }

    public boolean isOpen(int row, int col){
        if (row*col == 0 || row > nodes.length || col > nodes.length)
            throw new IndexOutOfBoundsException();
        return nodes[row-1][col-1];
    }

    public boolean isFull(int row, int col){
        if (row*col == 0 || row > nodes.length || col > nodes.length)
            throw new IndexOutOfBoundsException();
        int p = (row - 1)*nodes.length + col;
        return connectivity.connected(0, p);
    }// is site (row, col) full? => Open site that can be connected to an open site in the top row via a chain of neighboring open sites

    public int numberOfOpenSites(){
        int n_open = 0;
        for (int i = 0; i < nodes.length; i++){
            for (int j = 0; j < nodes.length; j++){
                if (nodes[i][j]) n_open += 1;
            }
        }
        return n_open;
    }
    
    public boolean percolates(){
        return connectivity.connected(0, n_conn - 1);
    }
    
    private void display(){
        StdDraw.setScale(-.5, nodes.length - .5);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        // Draw nodes
        for (int i = 0; i < nodes.length; i++){
            for (int j = 0; j < nodes.length; j++){
                if (isFull(i + 1, j + 1)){
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledRectangle(j, nodes.length - i - 1, 0.49, 0.49);
                }
                else if (nodes[i][j]){
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledRectangle(j, nodes.length - i - 1, 0.49, 0.49);
                }
            }
        }
    }
     
    public static void main(String[] args) // optional test client
    {
     Percolation percolation = new Percolation(20);
     
     Random rng = new Random();
     int n_open = 100;
     for (int i = 0; i < n_open; i++){
         percolation.open(rng.nextInt(percolation.nodes.length)+1,
                          rng.nextInt(percolation.nodes.length)+1);
     }

     // Tests
     System.out.println("Site (1, 1) open? " + percolation.isOpen(1, 1));
     System.out.println("Site (3, 4) open? " + percolation.isOpen(3, 4));
     System.out.println("Site (1, 2) connected to top? " + percolation.isFull(1, 2));
     System.out.println("Site (5, 3) connected to top? " + percolation.isFull(5, 3));
     System.out.println("Site (4, 2) connected to top? " + percolation.isFull(4, 2));
     System.out.println("Number of open sites: " + percolation.numberOfOpenSites());
     System.out.println("Percolates: " + percolation.percolates());
     percolation.display();
     System.out.println("Run complete.");
    }
        
}

/* Corner cases: 
 * - Row and column indices are between 1 and n
 * - (1, 1) is upper-left site
 * - Throw java.lang.IndexOutOfBoundsException if any argument to isOpen() or isFull() is outside its range
 * - Throw java.lang.IllegalArgumentException if n <= 0
 */