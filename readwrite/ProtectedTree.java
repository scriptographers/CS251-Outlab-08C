package readwrite;

public class ProtectedTree {
  private Tree tree;

  ProtectedTree(Tree t) {
    tree = t;
  }

  public void write(int value) {
    // sync logic
    System.out.println("WE");
    this.tree.write(value);
    System.out.println("WX");
    // sync logic
  }

  public int read(int value) {
    // sync logic
    int answer = this.tree.read(value);
    if (answer == value)
      System.out.println("RS");
    else
      System.out.println("RF");
    // sync logic
    return answer;
  }

  public static void main(String[] args) {
    System.out.println("Test 1");
    ProtectedTree ptree = new ProtectedTree(new Tree());
    ptree.write(56);
    ptree.write(52);
    ptree.write(55);
    ptree.write(47);
    System.out.println(ptree.read(53));
    System.out.println(ptree.read(52));

    System.out.println("Test 2");
    ptree = new ProtectedTree(new Tree());
    ptree.write(55);
    ptree.write(52);
    ptree.write(47);
    ptree.write(56);
    System.out.println(ptree.read(53));
    System.out.println(ptree.read(52));

  }
}
