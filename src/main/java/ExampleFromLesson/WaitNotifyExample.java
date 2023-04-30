package ExampleFromLesson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExample {
    public static void main(String [] args){
        Store store = new Store();

        final ExecutorService executorService = Executors.newFixedThreadPool(8);

        executorService.execute(new Producer(store));

        executorService.execute(new Consumer(store));

        executorService.shutdown();


       // new Thread(consumer).start();
    }


}
