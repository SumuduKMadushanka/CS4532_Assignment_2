import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        // main code
        float riderArrivalMeanTime = 3f * 1000;
        float busArrivalMeanTime = 2 * 60f * 1000 ;
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("\n*******  Press any key to exit.  *******\n" );

        BusStop busStop = new BusStop();

        RiderGenerator riderGenerator = new RiderGenerator(riderArrivalMeanTime, busStop);
        (new Thread(riderGenerator)).start();

        BusGenerator busGenerator = new BusGenerator(busArrivalMeanTime, busStop);
        (new Thread(busGenerator)).start();

        while(true){
            userInput = scanner.nextLine();
            if(userInput != null) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}