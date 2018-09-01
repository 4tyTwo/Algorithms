public class DynamicArrayStack<Item> implements Stack<Item> {

  private Item[] data;
  private int n;

  public DynamicArrayStack(){
    data = (Item[]) new Object[1];
    n = 0;
  }

  public void push(Item elem){
    if (n == data.length)
      resize(2 * data.length);
    data[n++] = elem;
  }

  public Item pop(){
    Item elem = data[--n];
    if (n > 0 && n <= data.length/4)
      resize(data.length/2);
    return elem;
  }

  public boolean isEmpty(){
    return n == 0;
  }

  private void resize(int newSize){
    Item[] newData = (Item[]) new Object[newSize];
    System.arraycopy(data,0, newData, 0, n);
    data = newData;
  }

}
