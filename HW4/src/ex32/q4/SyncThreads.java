package ex32.q4;

public class SyncThreads implements Runnable {

    private static Integer sum = 0;
    private static boolean syncOn = false;

    public static void main(String[] args) throws Exception {

        System.out.println("Start 1000 threads");
        Thread[] threads = makeThreads(1000);
        for (Thread t : threads) {
            t.start();
        }
        for(Thread t : threads){
            //make sure every single thread has been finished
            while(t.isAlive());
        }

        System.out.println("Start Sync'd 1000 threads");
        sum = 0;
        Thread[] threadsSync = makeThreads(1000);
        syncOn = true;
        for(Thread ts : threadsSync){
            ts.start();
            ts.join();
        }



    }

    public static Thread[] makeThreads(int count) throws Exception {
        if (count < 1 || count > 100000000) {
            throw new Exception("Too few or too many threads created");
        }
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(new SyncThreads());
        }
        return threads;
    }

    public static void startThreads(Thread[] threads) {
        for (Thread t : threads) {
            t.start();
        }
        return;
    }

    @Override
    public void run() {
        incrementSum();
    }

    private void incrementSum(){
        sum++;
        System.out.println(sum);
    }
}

