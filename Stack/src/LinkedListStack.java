public class LinkedListStack<Item> implements Stack<Item> {

  class Node {
    private Item item;
    private Node next;

    Node(Item elem, Node node){
      item = elem;
      next = node;
    }
  }

  private Node first = null;

  public void push(Item elem) {
    Node oldFirst = first;
    first = new Node(elem, oldFirst);
  }

  public Item pop() {
      Item item = first.item;
      first = first.next;
      return item;
  }

  public boolean isEmpty() {
    return first == null;
  }

}
