public class QuickFindUF
{
    private int[] id;

    public QuickFindIF(int N) // Constructor
    {
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public boolean find(int p, int q)
    { return id[p] == id[q];} // Returns whether elements q and p in id are conncted

    public void union(int p, int q) // Modifies id to give components of id[p] and  id[q] the same component id
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid) id[i] = qid; // Using id[p] would be wrong - the program would only modify ids up to i==p before failing
        }
    }
}
