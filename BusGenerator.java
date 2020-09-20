import java.util.Random;

public class BusGenerator implements Runnable {
    private float arrivalMeanTime;
    private BusStop busStop;
    private static Random random;

    public BusGenerator(float arrivalMeanTime, BusStop busStop) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.busStop = busStop;
        this.random = new Random();
    }

    @Override
    public void run() {
        int busID = 0;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Bus bus = new Bus(busID, busStop, busStop.getRiderBoardingSemaphore(), busStop.getBUsDepartSemaphore(), busStop.getMutex());
                (new Thread(bus)).start();
                busID++;

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
