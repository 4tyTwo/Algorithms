public class QuickUnionUF extends UnionFind {

  QuickUnionUF(int n){
    super(n);
  }

  public void union(int p, int q){
    int i = root(p);
    int j = root(q);
    data[i] = j;
  }

  public boolean connected(int p, int q){
    return root(p) == root(q);
  }

  private int root(int i){
    while (i != data[i])
      i = data[i];
    return i;
  }

}
