/* Returns the probability of k success in N Bernoulli trials with success
 * probability of p.
 */

// Estimate the number of recursive calls by binomial(100, 50, .25)
// : N = 100, k = 50 -> 102 calls, since we decrement k 51 times, and call
// binomial1 once per decrementation.
// Develop a better implementation based on saving computed values in an array

public class Binomial{
    
    // Slow way - recursive calculation each time
    public static double binomial1(int N, int k, double p){
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p)*binomial1(N-1, k, p) + p*binomial1(N-1, k-1, p); // Base case is when N == 0 || k = 0
    }
    
    // Faster way - build a look-up table
    public static double binomial2(int N, int k, double p){
        double[][] b = new double[N+1][k+1]; // 2D table to store N-k combination probabilities
        
        // Base cases for k = 0, all Ns
        for (int i = 0; i <= N; i++)
            b[i][0] = Math.pow(1.0 - p, i); // P(X = 0) = (1-p)^N
        b[0][0] = 1.0; // Define pathological case
        
        // Recursive formula
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= k; j++){
                b[i][j] = p*b[i-1][j-1] + (1.0 - p)*b[i-1][j]; // Exploit preceding cases being already computed
            }
        }
        return b[N][k];
    }
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        StdOut.println(binomial1(N, k, p));
        StdOut.println(binomial2(N, k, p));
    }
}