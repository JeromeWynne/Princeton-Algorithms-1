public class WeightedQuickUnionUF
{
    private int id[];
    private int sz[]; // Tree size

    public WeightedQuickUnionUF(int N){
        for (i = 0; i<N; i++){
            id[i] = i;
            sz[i] = 1;

        }
    }

    private int root(int i){
        while (id[i] != i){
            id[i] = id[id[i]] // Set each touched node to point to its grandparent
            i = id[i];
         }
        return i;
    }

    public boolean find(int p, int q){
        // Check what the roots of elements p and q are
        return root(q) == root(p);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j])  { id[i] = j; sz[j] += sz[i]}
        else                { id[j] = i; sz[i] += sz[j]}
    }
}
