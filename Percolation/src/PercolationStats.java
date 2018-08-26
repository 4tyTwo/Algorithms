import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;
  private final double mean, stddev, confidenceLo, confidenceHi;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0)
      throw new java.lang.IllegalArgumentException("Arguments can't 0 or negative");
    double numOfElements = (int) Math.pow(n, 2);
    double[] percolationData = new double[trials];
    for (int i = 0; i < trials; ++i) {
      Percolation p = new Percolation(n);
      int row, col;
      while (!p.percolates()) {
        do {
          row = 1 + StdRandom.uniform(n);
          col = 1 + StdRandom.uniform(n);
        } while (p.isOpen(row, col));
        p.open(row, col);
      }
      percolationData[i] = p.numberOfOpenSites() / numOfElements;
    }
    mean = StdStats.mean(percolationData);
    if (percolationData.length == 1)
      stddev = Double.NaN;
    else
      stddev = StdStats.stddev(percolationData);
    confidenceHi = mean + (CONFIDENCE_95 * stddev)/Math.sqrt(percolationData.length);
    confidenceLo = mean - (CONFIDENCE_95 * stddev)/Math.sqrt(percolationData.length);

  }

  public double mean() {
    return mean;
  }

  public double stddev() {
    return stddev;
  }

  public double confidenceLo() {
    return confidenceLo;
  }

  public double confidenceHi() {
    return confidenceHi;
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    int trials = StdIn.readInt();
    PercolationStats PS = new PercolationStats(n, trials);
    StdOut.println("Mean: " + PS.mean());
    StdOut.println("Standard deviation: " + PS.stddev());
    StdOut.println("Confidence interval: [" + PS.confidenceLo() + ", " + PS.confidenceHi() + "]");
  }

}
