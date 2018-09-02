import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private class RandomizedQueueIterator implements Iterator<Item> {
    int curr = n;

    public boolean hasNext() {
      return curr > 0;
    }

    public Item next() {
      if ( curr == 0 )
        throw new java.util.NoSuchElementException();
      return data[--curr];
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

  }

  Item[] data;
  int n;
  // Can add some container to store empty fields

  public RandomizedQueue() {
    data = (Item[]) new Object[1];
    n = 0;
  }

  public boolean isEmpty() {
      return n == 0;
  }

  public int size() {
    return n;
  }

  public void enqueue(Item item) {
    if ( item == null )
      throw new java.lang.IllegalArgumentException();
    if (n == data.length)
      resize(n * 2);
    data[n++] = item;
  }

  public Item dequeue() {
    if ( n == 0 )
      throw new java.util.NoSuchElementException();
    int rnd = StdRandom.uniform(n);
    Item returned = data[rnd];
    shift(rnd);
    --n;
    if (n < data.length/4)
      resize(data.length/2);
    return returned;
  }

  public Item sample() {
    if (n == 0)
      throw new java.util.NoSuchElementException();
    int rnd = StdRandom.uniform(n);
    return data[rnd];
  }

  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  static public void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 10; ++i)
      rq.enqueue(StdRandom.uniform(25));
    StdOut.println("Iterating: ");
    for (int i : rq)
      StdOut.print(i + " ");
    StdOut.println("\nDequeueing: ");
    for (int i = 0; i < 10; ++i)
      StdOut.print(rq.dequeue() + " ");
  }

  private void shift(int pos) { // pos - position of deleted element
    int len  = n - pos;
    System.arraycopy(data, pos + 1, data, pos, len);
  }

  private void resize(int newSize){
    Item[] newData = (Item[]) new Object[newSize];
    System.arraycopy(data,0, newData, 0, n);
    data = newData;
  }

}
