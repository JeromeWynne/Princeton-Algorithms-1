import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF connectivity;
    private boolean[][] nodes;
    private int nConn;
    
    public Percolation(int n) {
       if (n <= 0) throw new IllegalArgumentException();
       nConn = (int) Math.pow(n, 2) + 2;
       connectivity = new WeightedQuickUnionUF(nConn);
       nodes = new boolean[n][n];
       for (int i = 0; i < nodes.length; i++) {
           for (int j = 0; j < nodes.length; j++) nodes[i][j] = false;
       }
    }
    
    private int ix2conn(int node_row, int node_col) {
        return node_row*nodes.length + (node_col + 1);
    }
    
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > nodes.length || col > nodes.length) {
            throw new IndexOutOfBoundsException();
        }
        row -= 1;
        col -= 1;
        int p = ix2conn(row, col);
        nodes[row][col] = true;
        int row_above = Math.max(row - 1, 0);
        int row_below = Math.min(row + 1, nodes.length - 1);
        int col_right = Math.min(col + 1, nodes.length - 1);
        int col_left  = Math.max(col - 1, 0);
        if (nodes[row_above][col])   connectivity.union(p, ix2conn(row_above, col));
        if (nodes[row_below][col])   connectivity.union(p, ix2conn(row_below, col));
        if (nodes[row][col_right])   connectivity.union(p, ix2conn(row, col_right));
        if (nodes[row][col_left])    connectivity.union(p, ix2conn(row, col_left));
        if (row == 0)                connectivity.union(0, p);
        if (row + 1 == nodes.length) connectivity.union(nConn - 1, p);
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > nodes.length || col > nodes.length) {
            throw new IndexOutOfBoundsException();
        }
        return nodes[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > nodes.length || col > nodes.length) {
            throw new IndexOutOfBoundsException();
        }
        int p = ix2conn(row - 1, col - 1);
        return connectivity.connected(0, p);
    }

    public int numberOfOpenSites() {
        int nOpen = 0;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (nodes[i][j]) nOpen += 1;
            }
        }
        return nOpen;
    }
    
    public boolean percolates() {
        return connectivity.connected(0, nConn - 1);
    }
     
    public static void main(String[] args) {
    } 
    
}