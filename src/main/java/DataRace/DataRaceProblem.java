package DataRace;

import java.util.List;

public class DataRaceProblem implements Runnable{

    private volatile static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataRaceProblem d1 = new DataRaceProblem();
        DataRaceProblem d2 = new DataRaceProblem();

        List<Thread> threads = List.of(new Thread(d1), new Thread(d2));

        for (Thread t: threads) t.start();
        for (Thread t: threads) t.join();

        System.out.println("Count: " + count);
    }

}
