package readwrite;

public class Tree {
  int value;
  Tree l, r;
  boolean dfn; // On initialization, the tree must have a node with no value
  // However, once a write has been done, all nodes would have some value.

  public Tree() {
    l = null;
    r = null;
    dfn = false;
  }

  public Tree(int i) {
    l = null;
    r = null;
    value = i;
    dfn = true;
  }

  public void write(int i) {
    if (!dfn) {
      value = i;
      dfn = true;
      return;
    } else {
      // Search left subtree
      if (value > i) {
        if (l == null)
          l = new Tree(i);
        else
          l.write(i);
      }
      // Search right subtree
      else if (value < i) {
        if (r == null)
          r = new Tree(i);
        else
          r.write(i);
      }
      // value cannot be equal to i (this can be assumed)
      // Even if it is, the tree should not be updated
      return;
    }
  }

  public int read(int i) {
    // Found i
    if (value == i)
      return i;

    // Search left subtree
    else if (value > i) {
      if (l != null)
        return l.read(i);
      else
        return value;
    }
    // Search right subtree
    else {
      if (r != null)
        return r.read(i);
      else
        return value;
    }
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
