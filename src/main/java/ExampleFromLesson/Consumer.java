package ExampleFromLesson;

public class Consumer implements Runnable {
    Store store;
    Consumer(Store store){
        this.store=store;
    }

    @Override
    public synchronized void run() {
        for(int i=1;i<6;i++){
            store.get();
        }
    }
}
