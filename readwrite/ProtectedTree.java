package readwrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProtectedTree {
  private Tree tree;
  private int writes;
  private int success_reads;

  private final Lock lock = new ReentrantLock();
  private final Condition read_check = lock.newCondition();

  public ProtectedTree(Tree t) {
    tree = t;
    writes = 0;
    success_reads = 0;
  }

  public void write(int value) throws InterruptedException {
    lock.lock();
    System.out.println("WE");
    this.tree.write(value);
    System.out.println("WX");
    writes++;
    read_check.signal();
    lock.unlock();
  }

  public int read(int value) throws InterruptedException {
    lock.lock();
    // Check validity of read:
    while (success_reads >= writes)
      read_check.await();
    // Read the value:
    int answer = this.tree.read(value);
    if (answer == value){
      System.out.println("RS");
      success_reads++;
    }
    else
      System.out.println("RF");
    read_check.signal();
    lock.unlock();
    return answer;
  }
}
