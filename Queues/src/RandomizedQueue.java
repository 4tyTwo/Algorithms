import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] data;
  private int n;

  private class RandomizedQueueIterator implements Iterator<Item> {

    private int curr = n;

    public RandomizedQueueIterator() {
      StdRandom.shuffle(data, 0, n);
    }

    public boolean hasNext() {
      return curr > 0;
    }

    public Item next() {
      if (curr == 0)
        throw new java.util.NoSuchElementException();
      return data[--curr];
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

  }

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
    if (item == null)
      throw new java.lang.IllegalArgumentException();
    if (n == data.length)
      resize(n * 2);
    data[n++] = item;
  }

  public Item dequeue() {
    if (n == 0)
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

  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    for (int i = 0; i < 25; ++i)
      rq.enqueue(StdRandom.uniform(1000));
    for (int j = 1; j < 3; ++j){
      StdOut.println("Iterator " + j);
      for (int i : rq)
        StdOut.print(i + " ");
      StdOut.println();
    }


  }

  private void shift(int pos) { // pos - position of deleted element
    for (int i = pos; i < n - 1; ++i)
      data[i] = data[i + 1];
  }

  private void resize(int newSize){
    Item[] newData = (Item[]) new Object[newSize];
    System.arraycopy(data, 0, newData, 0, n);
    data = newData;
  }

}
