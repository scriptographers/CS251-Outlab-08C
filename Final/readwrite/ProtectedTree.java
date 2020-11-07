package readwrite;

public class ProtectedTree {
  private Tree tree;
  private int writes;
  private int success_reads;

  public ProtectedTree(Tree t) {
    tree = t;
    writes = 0;
    success_reads = 0;
  }

  public void write(int value) throws InterruptedException {
    synchronized (this) {
      System.out.println("WE");
      this.tree.write(value);
      System.out.println("WX");
      writes++;
      notify();
    }
  }

  public int read(int value) throws InterruptedException {
    synchronized (this) {
      while (!(writes > success_reads)) {
        wait();
      }
      int answer = this.tree.read(value);
      if (answer == value)
        System.out.println("RS");
      else
        System.out.println("RF");
      if (answer == value) {
        success_reads++;
      }
      notify();
      return answer;
    }
  }
}
