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
    // sync logic
    lock.lock();

    // Write the value in tree:
    System.out.println("WE");
    this.tree.write(value);
    System.out.println("WX");

    // sync logic
    writes++;
    read_check.signal();
    lock.unlock();
  }

  public int read(int value) throws InterruptedException {
    // sync logic
    lock.lock();
    while (success_reads >= writes) // Check validity of read
      read_check.await();

    // Read the value:
    int answer = this.tree.read(value);
    if (answer == value)
      System.out.println("RS");
    else
      System.out.println("RF");

    // sync logic
    if (answer == value)
      success_reads++;
    read_check.signal();
    lock.unlock();
    return answer;
  }
}
