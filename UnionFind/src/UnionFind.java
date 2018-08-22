import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class UnionFind {

  private int[] data;

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

  public static void main(String[] args){
    try {
      System.setIn(new FileInputStream(new File("Input/input.txt")));
    }
    catch (Exception e){
      e.printStackTrace(System.out);
    }
    int n = StdIn.readInt();
    int p, q;
    UnionFind uf = new UnionFind(n);
    while (!StdIn.isEmpty()){
      p = StdIn.readInt();
      q = StdIn.readInt();
      if (!uf.connected(p, q)){
        uf.union(p, q);
        StdOut.println(p + " " + q);
      }
    }
  }

}
