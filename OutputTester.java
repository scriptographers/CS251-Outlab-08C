import java.io.*;
import java.util.*;

public class OutputTester {

  public static <T> void print(T s) { System.out.println(s); }

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);

    int n = 220200;

    // First word:
    String temp = sc.nextLine().trim();
    if (!temp.equals("WE")) {
      print("Wrong (1)");
      return;
    }
    String prev = temp;

    int nw = 1, nr = 0;

    for (int i = 1; i < n; i++) {
      temp = sc.nextLine().trim();

      if (prev.equals("WE")) {
        if (!temp.equals("WX"))
          print("Wrong (2)");
      }

      if (temp.equals("WE"))
        nw++;
      else if (temp.equals("RS"))
        nr++;

      if (nr > nw)
        print("Wrong (4)");

      prev = temp;
    }

    sc.close();
  }
}
