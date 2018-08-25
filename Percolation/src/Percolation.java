import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private WeightedQuickUnionUF uf;
  private int data[][]; //Represents info about opened/closed data
  private int openedSites;
  private int size;
  private int virtualBottom, virtualTop;

  public Percolation(int n){
    if (n <= 0)
      throw new java.lang.IllegalArgumentException("Size can't be 0 or negative");
    size = n;
    uf = new WeightedQuickUnionUF((n * n) + 2);
    virtualTop = (size * size);
    virtualBottom = virtualTop + 1;
    openedSites = 0;
    data = new int[n][n];
    for (int i = 0; i < n; ++i)
      for (int j = 0; j < n; ++j)
        data[i][j] = 0;
  }

  public void open(int row, int col){
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    data[row - 1][col -1] = 1; //Open
    ++openedSites;
    uniteAdjustments(row, col);
  }

  public boolean isOpen(int row, int col){
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    return data[row - 1][col -1] == 1;
  }

  public boolean isFull(int row, int col){
    //Check if connected to virtualTop
    if (!(checkSize(row) && checkSize(col)))
      throw new java.lang.IllegalArgumentException("index is out of boundaries");
    return uf.connected(toArrayIndex(row, col), virtualTop);
  }

  public int numberOfOpenSites(){
    return openedSites;
  }

  public boolean percolates(){
    return uf.connected(virtualBottom, virtualTop);
  }

  private boolean checkSize(int checked){
    return (checked > 0 && checked <= size);
  }

  private int toArrayIndex(int row, int col){
    return (row - 1) * size + col - 1;
  }

  private void uniteAdjustments(int row, int col){
    int curr = toArrayIndex(row, col);
    if (row == 1)
      uf.union(curr, virtualTop);
    if (row == size)
      uf.union(curr, virtualBottom);
    if (checkSize(row-1))
      if (isOpen(row - 1, col))
        uf.union(curr - size, curr);
    if (checkSize(row + 1))
      if (isOpen(row + 1, col))
        uf.union(curr + size, curr);
    if (checkSize(col - 1))
      if (isOpen(row, col - 1))
        uf.union(curr - 1, curr);
    if (checkSize(col + 1))
      if (isOpen(row, col + 1))
        uf.union(curr + 1, curr);
  }

}
