package readwrite;

import java.io.File;
import java.util.Scanner;

public class ReaderWriter implements Runnable {
  private String name;
  private ProtectedTree tree;
  private boolean isw;

  public ReaderWriter(String FILENAME, ProtectedTree ptree, boolean iswriter) {
    name = FILENAME;
    tree = ptree;
    isw = iswriter;
  }

  @Override
  public void run() {
    try {
      File file = new File(name);
      Scanner sc = new Scanner(file);

      if (isw) {
        while (sc.hasNextLine()) {
          int i = Integer.parseInt(sc.nextLine());
          tree.write(i);
        }
      } 
      else {
        while (sc.hasNextLine()) {
          int i = Integer.parseInt(sc.nextLine());
          tree.read(i);
        }
      }
      sc.close();
    } 
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return;
  }
}
