package readwrite;

public class ProtectedTree {
  private Tree tree;
  private int writes;
  private int success_reads;
  private boolean active;

  public ProtectedTree(Tree t) {
    tree = t;
    writes = 0;
    success_reads = 0;
    active = false;
  }

  public void write(int value) throws InterruptedException {
    // sync logic
    synchronized (this) {
      while (active) {
        wait();
      }
      active = true;
    }
    System.out.println("WE");
    this.tree.write(value);
    System.out.println("WX");
    // sync logic
    synchronized (this) {
      writes++;
      active = false;
      notify();
    }
  }

  public int read(int value) throws InterruptedException {
    // sync logic
    synchronized (this) {
      while (active || !(writes > success_reads)) {
        wait();
      }
      active = true;
    }
    int answer = this.tree.read(value);
    System.out.println(answer + "//" + value);
    if (answer == value)
      System.out.println("RS");
    else
      System.out.println("RF");
    // sync logic
    synchronized (this) {
      if (answer == value) {
        success_reads++;
      }
      active = false;
      notify();
    }
    return answer;
  }

  // public static void main(String[] args) {
  // System.out.println("Test 1");
  // ProtectedTree ptree = new ProtectedTree(new Tree());
  // ptree.write(56);
  // ptree.write(52);
  // ptree.write(55);
  // ptree.write(47);
  // System.out.println(ptree.read(53));
  // System.out.println(ptree.read(52));

  // System.out.println("Test 2");
  // ptree = new ProtectedTree(new Tree());
  // ptree.write(55);
  // ptree.write(52);
  // ptree.write(47);
  // ptree.write(56);
  // System.out.println(ptree.read(53));
  // System.out.println(ptree.read(52));

  // }
}
