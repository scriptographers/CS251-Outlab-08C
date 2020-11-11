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
    while (!(writes > success_reads)) {
      read_check.await();
    }
    int answer = this.tree.read(value);
    if (answer == value)
      System.out.println("RS");
    else
      System.out.println("RF");
    if (answer == value) {
      success_reads++;
    }
    read_check.signal();
    lock.unlock();
    return answer;
  }
}
