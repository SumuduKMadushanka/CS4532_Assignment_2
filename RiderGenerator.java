import java.util.Random;

public class RiderGenerator implements Runnable {
    private float arrivalMeanTime;
    private BusStop busStop;
    private static Random random;

    public RiderGenerator(float arrivalMeanTime, BusStop busStop) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.busStop = busStop;
        this.random = new Random();
    }

    @Override
    public void run() {
        int riderID = 1;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Rider rider = new Rider(riderID, busStop, busStop.getRiderEntranceSemaphore(), busStop.getRiderBoardingSemaphore(), busStop.getBUsDepartSemaphore(), busStop.getMutex());
                (new Thread(rider)).start();
                riderID++;

                Thread.sleep(getExponentiallyDistributedRiderInterArrivalTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getExponentiallyDistributedRiderInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }

}
