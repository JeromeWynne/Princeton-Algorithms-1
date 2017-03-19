import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] threshold;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
           Percolation perc = new Percolation(n);
           while (!perc.percolates()) {
             int p = StdRandom.uniform(n) + 1;
             int q = StdRandom.uniform(n) + 1;
             perc.open(p, q);
           }
           threshold[i] = (double) perc.numberOfOpenSites()/(Math.pow(n, 2));
        }
    }
    
    public double mean() {
        return StdStats.mean(threshold);
    }
    
    public double stddev() {
        return StdStats.stddev(threshold);
    }
    
    public double confidenceLo() {
        double mu = mean();
        double std = stddev();
        return mu - (1.96*std/(Math.sqrt(threshold.length)));
    }
    
    public double confidenceHi() {
        double mu = mean();
        double std = stddev();
        return mu + (1.96*std/(Math.sqrt(threshold.length)));
    }
    
    public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats test = new PercolationStats(n, T);
    System.out.println("mean = " + test.mean());
    System.out.println("stddev = " + test.stddev());
    System.out.println("95% confidence interval = ["+test.confidenceLo()+
                       ", "+test.confidenceHi()+"]");
    }
}