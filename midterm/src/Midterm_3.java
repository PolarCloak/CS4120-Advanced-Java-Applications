import java.util.concurrent.*;



public class Midterm_3 {

    private Integer sum = new Integer(0);



    public static void main(String[] args) {
        Midterm_3 test = new Midterm_3();
        System.out.println("What is sum ? " + test.sum);
    }



    public Midterm_3() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        Task task = new Task();
        for (int i = 0; i < 500; i++) {
            executor.execute(task);
        }
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
    }


    class Task implements Runnable {
        public synchronized void run() {
            sum++;
        }//end run
    }//end Task

}//end Midterm_3