public class WeightedUF extends QuickUnionUF {

  //This class implements weighted trees, that are shallower than in superclass, so the find operation is faster

  public int[] treeSize;

  public WeightedUF(int n){
    super(n);
    treeSize = new int[n];
    for (int i = 0; i < n; ++i)
      treeSize[i] = 1;
  }

  @Override
  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    if (i == j)
      return;
    if (treeSize[i] < treeSize[j]){
      data[i] = j;
      treeSize[j] += treeSize[i];
    } else {
      data[j] = i;
      treeSize[i] += treeSize[j];
    }
  }

  @Override
  protected int root(int i) {
      while (i != data[i]) {
        data[i] = data[data[i]]; //Now each element of the tree points to it's grandparent. It's flattening the tree
        i = data[i];
      }
      return i;
  }
}


