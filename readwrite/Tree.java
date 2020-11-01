package readwrite;

public class Tree {
  class Node {
    int value;
    Node l, r;

    public Node(int item) {
      value = item;
      l = r = null;
    }
  }

  Node root;

  Tree() {
    root = null;
  }

  private Node insert(Node n, int i) {
    if (n == null) {
      n = new Node(i);
      return n;
    } else {
      if (i < n.value) {
        n.l = insert(n.l, i);
      } else if (i > n.value) {
        n.r = insert(n.r, i);
      }
      return n;
    }
  }

  private int search(Node n, int i) {
    if (n.value == i) {
      return i;
    } else if (n.value > i) {
      if (n.l != null) {
        return search(n.l, i);
      } else {
        return n.value;
      }
    } else {
      if (n.r != null) {
        return search(n.r, i);
      } else {
        return n.value;
      }
    }
  }

  public void write(int i) {
    root = insert(root, i);
  }

  public int read(int i) {
    return search(root, i);
  }

  public static void main(String[] args) {
    Tree obj1 = new Tree();
    obj1.write(56);
    obj1.write(52);
    obj1.write(55);
    obj1.write(47);
    System.out.println(obj1.read(53));

    Tree obj2 = new Tree();
    obj2.write(55);
    obj2.write(52);
    obj2.write(47);
    obj2.write(56);
    System.out.println(obj2.read(53));
  }
}
