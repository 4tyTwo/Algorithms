public class DynamicArrayStack implements Stack {

  int[] data;
  int n;

  public DynamicArrayStack(){
    data = new int[1];
    n = 0;
  }

  public void push(int elem){
    if (n == data.length)
      resize(2 * data.length);
    data[n++] = elem;
  }

  public int pop(){
    int elem = data[--n];
    if (n > 0 && n <= data.length/4)
      resize(data.length/2);
    return elem;
  }

  public boolean isEmpty(){
    return n == 0;
  }

  private void resize(int newSize){
    int[] newData = new int[newSize];
    for (int i = 0; i < n; ++i)
      newData[i] = data[i];
    data = newData;
  }

}
