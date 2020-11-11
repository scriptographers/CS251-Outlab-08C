import java.util.Random;
import java.util.*;

public class InputGenerator {

  public static void main(String args[]) {
    Random rand = new Random();

    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine().trim());

    for (int i = 0; i < n; i++) {
      System.out.println(rand.nextInt(1000));
    }

    sc.close();
  }
}
