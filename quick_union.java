public class QuickUnionUF
{
    private int id[];

    public QuickUnionUF(int N){
        for (i = 0; i<N; i++) id[i] = i; // Everything is its own root to begin with
    }

    private int root(int i){
        while (id[i] != i) i = id[i];
        return i;
    }

    public boolean find(int p, int q){
        // Check what the roots of elements p and q are
        return root(q) == root(p);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
