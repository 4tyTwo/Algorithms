import java.io.File;
import java.io.FileInputStream;
public class UnionFind {

  protected int[] data;

  UnionFind(int n){
    data = new int[n];
    for (int i = 0; i < n; ++i)
      data[i] = i;
  }

  public boolean connected(int p, int q){
    //Leave indexOutOfRange Unchecked
    return data[p] == data[q];
  }

  public void union(int p, int q){
    //Leave indexOutOfRange Unchecked
    if (!connected(p,q)) {
      int oldId = data[p];
      int newId = data[q];
      for (int i = 0; i < data.length; ++i)
        if (data[i] == oldId)
          data[i] = newId;
    }
  }

  //Solution for interview question
  public int find(int i){
    //Returns the largest element, connected to element i
    int max = i;
    for (int j = i + 1; j < data.length; ++j){
      if (data[j] == data[i])
        max = j;
    }
    return max;
  }

  public static void main(String[] args){
    try {
      System.setIn(new FileInputStream(new File("Input/input.txt")));
    }
    catch (Exception e){
      e.printStackTrace(System.out);
    }
    int n = StdIn.readInt();
    int p, q;
    WeightedUF uf = new WeightedUF(n);
    while (!StdIn.isEmpty()) {
      p = StdIn.readInt();
      q = StdIn.readInt();
      if (!uf.connected(p, q)) {
        uf.union(p, q);
        StdOut.println(p + " " + q);
      }
    }
    for (int i = 0; i < uf.treeSize.length; ++i)
      StdOut.print("[" +i + "] " + uf.treeSize[i] + ";");
  }

}
