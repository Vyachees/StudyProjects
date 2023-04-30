package ExampleFromLesson;

public class Producer implements  Runnable{
    Store store;

    Producer(Store store){
        this.store=store;
    }

    @Override
    public synchronized void run() {
        for(int i=1;i<6;i++){
            store.put();
        }
        try {
            Thread.sleep(5000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
