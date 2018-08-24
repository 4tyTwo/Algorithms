public class QuickUnionUF extends UnionFind {

  QuickUnionUF(int n){
    super(n);
  }

  @Override
  public void union(int p, int q){
    int i = root(p);
    int j = root(q);
    data[i] = j;
  }

  @Override
  public boolean connected(int p, int q){
    return root(p) == root(q);
  }

  protected int root(int i){
    while (i != data[i])
      i = data[i];
    return i;
  }

}
