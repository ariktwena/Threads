package DataRace;

import java.util.List;

public class DataRaceNormal implements Runnable{

    private volatile static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataRaceNormal dataRaceNormal = new DataRaceNormal();
        dataRaceNormal.run();

        System.out.println("Count: " + count);

    }
}
