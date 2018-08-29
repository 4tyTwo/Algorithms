public class LinkedListStack implements Stack {

  class Node {
    public int item;
    public Node next;

    Node(int elem, Node node){
      item = elem;
      next = node;
    }
  }

  private Node first = null;

  public void push(int elem) {
    Node oldFirst = first;
    first = new Node(elem, oldFirst);
  }

  public int pop() {
      int item = first.item;
      first = first.next;
      return item;
  }

  public boolean isEmpty() {
    return first == null;
  }

}
