import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private boolean[][] data; // Represents info about opened/closed data
  private int openedSites;
  private final WeightedQuickUnionUF uf;
  private final int size;
  private final int virtualBottom, virtualTop;

  public Percolation(int n) {
    if (n <= 0)
      throw new java.lang.IllegalArgumentException("Size can't be 0 or negative");
    size = n;
    uf = new WeightedQuickUnionUF((n * n) + 2);
    virtualTop = (size * size);
    virtualBottom = virtualTop + 1;
    openedSites = 0;
    data = new boolean[n][n];
  }

  public void open(int row, int col) {
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    if (!data[row - 1][col - 1]) {
      data[row - 1][col - 1] = true; // Open
      ++openedSites;
      uniteAdjustments(row, col);
    }
  }

  public boolean isOpen(int row, int col) {
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    return data[row - 1][col -1];
  }

  public boolean isFull(int row, int col) {
    // Check if connected to virtualTop AND has full neighbour
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    return  uf.find(toArrayIndex(row, col)) == uf.find(virtualTop);
  }

  public int numberOfOpenSites() {
    return openedSites;
  }

  public boolean percolates() {
    return uf.connected(virtualBottom, virtualTop);
  }

  private boolean checkSize(int checked) {
    return (checked > 0 && checked <= size);
  }

  private int toArrayIndex(int row, int col) {
    return (row - 1) * size + col - 1;
  }

  private void uniteAdjustments(int row, int col) {
    int curr = toArrayIndex(row, col);
    if (row == 1)
      uf.union(curr, virtualTop);
    if (row == size)
      uf.union(curr, virtualBottom);
    if (checkSize(row-1) && isOpen(row - 1, col))
        uf.union(curr - size, curr);
    if (checkSize(row + 1) && isOpen(row + 1, col))
        uf.union(curr + size, curr);
    if (checkSize(col - 1) && isOpen(row, col - 1))
        uf.union(curr - 1, curr);
    if (checkSize(col + 1) && isOpen(row, col + 1))
        uf.union(curr + 1, curr);
  }

}
