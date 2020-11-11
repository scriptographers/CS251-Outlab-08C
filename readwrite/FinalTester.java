package readwrite;

public class FinalTester{
    public static void main(String[] args) throws InterruptedException{
        ProtectedTree ptree = new ProtectedTree(new Tree());
        Thread r1 = new Thread(new ReaderWriter("r1.txt", ptree, false));
        Thread r2 = new Thread(new ReaderWriter("r2.txt", ptree, false));
        Thread w1 = new Thread(new ReaderWriter("w1.txt", ptree, true));
        Thread w2 = new Thread(new ReaderWriter("w2.txt", ptree, true));

        r1.start(); r2.start(); w1.start(); w2.start();
        r1.join(); r2.join(); w1.join(); w2.join();
    }
}