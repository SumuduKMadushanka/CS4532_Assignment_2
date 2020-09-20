import java.util.concurrent.Semaphore;

public class Rider implements Runnable{
    private final int index;
    private BusStop busStop;
    private static Bus bus = null;
    private final Semaphore riderEntranceSem;
    private final Semaphore riderBoardingBusSem;
    private final Semaphore busDepartSem;
    private final Semaphore mutex;

    public Rider(int index, BusStop busStop, Semaphore riderEntranceSem, Semaphore riderBoardingBusSem, Semaphore busDepartSem, Semaphore mutex) {
        this.index = index;
        this.busStop = busStop;
        this.riderEntranceSem = riderEntranceSem;
        this.riderBoardingBusSem = riderBoardingBusSem;
        this.busDepartSem = busDepartSem;
        this.mutex = mutex;
    }
    
    @Override
    public void run() {
        try {
            this.riderEntranceSem.acquire();
                this.mutex.acquire();
                    this.arriveBusStop();
                this.mutex.release();

                this.riderBoardingBusSem.acquire();
                this.boardBus(bus);

            this.riderEntranceSem.release();

            if (busStop.isEmpty() || bus.getPCount() == 50) {
                this.busDepartSem.release();
            } else {
                this.riderBoardingBusSem.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateBus(Bus bus) {
        Rider.bus = bus;
    }

    public void arriveBusStop() {
        System.out.println("Rider " + index + " Arrived to the Bus Stop");
        busStop.arriveRider(this);
    }
    
    public void boardBus(Bus bus) {
        System.out.println("Rider " + index + " boarded to the Bus");
        bus.riderBoarded(this);
        busStop.leaveRider(this);
    }
}
