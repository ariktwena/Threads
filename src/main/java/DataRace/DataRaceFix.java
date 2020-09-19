package DataRace;

import java.util.List;

public class DataRaceFix implements Runnable{

    private static Object lock = new Object();
    private volatile static int count = 0;

    private final String name;

    public DataRaceFix(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        
        //Here the first finises and then the next starts
        synchronized (lock) {
            System.out.println(name);
            for (int i = 0; i < 10000; i++) {
                count += 1;
            }
        }
        
        //Here the two run simultaneous and switch adding the count
        System.out.println(name);
            synchronized (lock) {
            for (int i = 0; i < 10000; i++) {
                count += 1;
            }
        }
    }

    // Example1 === Example2
    //Here only one can access => example player.isTrue => if statement
    public synchronized void example1() {
        /// ...
    }
    // Example1 === Example2
    //Here only one can access => example player.isTrue => if statement
    public void example2() {
        synchronized (this) {
            /// ...
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataRaceFix d1 = new DataRaceFix("D1");
        DataRaceFix d2 = new DataRaceFix("D2");

        List<Thread> threads = List.of(new Thread(d1), new Thread(d2));

        for (Thread t: threads) t.start();
        for (Thread t: threads) t.join();

        System.out.println("Count: " + count);
    }
    

}
