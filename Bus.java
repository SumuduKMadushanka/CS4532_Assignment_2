import java.util.concurrent.Semaphore;

public class Bus implements Runnable {
    private int index;
    private BusStop busStop;
    private Rider[] passengers;
    private int p_count;
    private final Semaphore riderBoardingBusSem;
    private final Semaphore busDepartSem;
    private final Semaphore mutex;

    public Bus(int index, BusStop busStop, Semaphore riderBoardingBusSem, Semaphore busDepartSem, Semaphore mutex) {
        this.index = index;
        this.busStop = busStop;
        passengers = new Rider[50];
        p_count = 0;
        this.riderBoardingBusSem = riderBoardingBusSem;
        this.busDepartSem = busDepartSem;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            this.mutex.acquire();
            this.arrived();
            
            if (!busStop.isEmpty()) {
                System.out.println("There are " + busStop.getRiderCount() + " waiting");
                this.riderBoardingBusSem.release();
                this.busDepartSem.acquire();
            }

            this.mutex.release();
            depart();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrived() {
        System.out.println("Bus " + index + " arrived to bus stop");
        busStop.updateBus(this);
    }

    public int getPCount() {
        return p_count;
    }

    public void riderBoarded(Rider rider) {
        this.passengers[p_count++] = rider;
    }

    public void depart() {
        System.out.println("Bus departing with " + p_count + " riders");
        busStop.updateBus(null);
    }
    
}
