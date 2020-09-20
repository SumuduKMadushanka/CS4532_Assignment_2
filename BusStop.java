import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class BusStop {
    private static final int maxRiderCount = 50;
    private ArrayList<Rider> riders = new ArrayList<>();

    private static final Semaphore riderEntranceSem = new Semaphore(maxRiderCount);
    private static final Semaphore riderBoardingBusSem = new Semaphore(0);
    private static final Semaphore busDepartSem = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    public void arriveRider(Rider rider) {
        riders.add(rider);
    }
    public void leaveRider(Rider rider) {
        riders.remove(rider);
    }

    public boolean isEmpty() {
        return riders.isEmpty();
    }
    public int getRiderCount() {
        return riders.size();
    }

    public void updateBus(Bus bus) {
        Rider.updateBus(bus);
    }

    public static Semaphore getRiderEntranceSemaphore() {
        return riderEntranceSem;
    }
    public static Semaphore getRiderBoardingSemaphore() {
        return riderBoardingBusSem;
    }
    public static Semaphore getBUsDepartSemaphore() {
        return busDepartSem;
    }
    public static Semaphore getMutex() {
        return mutex;
    }

}
