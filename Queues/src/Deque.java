import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

  private Node first, last;
  private int n;

  private class Node {
    private final Item content;
    private Node next;
    private Node prev;

    public Node(Item content, Node next, Node prev) {
      this.content = content;
      this.next = next;
      this.prev = prev;
    }
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (current == null)
        throw new java.util.NoSuchElementException();
      Item item = current.content;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

  }

  public Deque() {
    first = null;
    last = null;
    n = 0;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public int size() {
    return n;
  }

  public void addFirst(Item item) {
    if (item == null)
      throw new java.lang.IllegalArgumentException("Cannot add null item");
    Node oldFirst = first;
    first = new Node(item, oldFirst, null);
    if (n == 0)
      last = first;
    else
      oldFirst.prev = first;
    ++n;
  }

  public void addLast(Item item) {
    if (item == null)
      throw new java.lang.IllegalArgumentException("Cannot add null item");
    Node oldLast = last;
    last = new Node(item, null, oldLast);
    if (n == 0)
      first = last;
    else
      oldLast.next = last;
    ++n;
  }

  public Item removeFirst() {
    if (n == 0)
      throw new java.util.NoSuchElementException();
    Item returned = first.content;
    first = first.next;
    --n;
    return returned;
  }

  public Item removeLast() {
    if (n == 0)
      throw new java.util.NoSuchElementException();
    Item returned = last.content;
    last = last.prev;
    --n;
    return returned;
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }


  public static void main(String[] Args) {
    Deque<Integer> dq = new Deque<Integer>();
    dq.addLast(1);
    dq.addLast(2);
    dq.addLast(3);
    dq.addFirst(4);
    dq.addFirst(5);
    dq.addLast(6);
    dq.addFirst(7);
    StdOut.println(dq.removeLast());
    StdOut.print(dq.size());
  }

}
