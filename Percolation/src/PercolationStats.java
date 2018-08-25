import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double percolations[];
  private int size, T;
  public PercolationStats(int n, int trials){
    if (n <= 0 || trials <= 0)
      throw new java.lang.IllegalArgumentException("Arguments can't 0 or negative");
    T = trials;
    double numOfElems = (int) Math.pow(n, 2);
    size = n;
    percolations = new double[T];
    for (int i = 0; i < T; ++i){
      Percolation p = new Percolation(size);
      int row, col;
      while (!p.percolates()) {
        do {
          row = 1 + StdRandom.uniform(size);
          col = 1 + StdRandom.uniform(size);
        } while (p.isOpen(row, col));
        p.open(row, col);
      }
     percolations[i] = p.numberOfOpenSites() / numOfElems;
    }
  }

  public double mean(){
    return StdStats.mean(percolations);
  }

  public double stddev(){
    return StdStats.stddev(percolations);
  }

  public double confidenceLo(){
    double s = stddev();
    double mean = mean();
    return (mean - (1.96 * s)/Math.sqrt(T));
  }

  public double confidenceHi(){
    double s = stddev();
    double mean = mean();
    return (mean + (1.96 * s)/Math.sqrt(T));
  }

  public static void main(String[] args){
    int n = StdIn.readInt();
    int trials = StdIn.readInt();
    PercolationStats PS = new PercolationStats(n, trials);
    StdOut.println("Mean: " + PS.mean());
    StdOut.println("Standard deviation: " + PS.stddev());
    StdOut.println("Confidence interval: [" + PS.confidenceLo() + ", " + PS.confidenceHi() + "]");
  }

}
