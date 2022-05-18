/*
Rewrite the Thread1000 to use a thread pool
 -try fixed thread pool
 -try cached thread pool
 */

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool1000 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //ExecutorService executor = Executors.newFixedThreadPool(1000);
        ExecutorService executor = Executors.newCachedThreadPool();

        Random rand = new Random();

        //for loop to initialize the threads
        for (int i = 0; i < 1000; i++){
            int x = rand.nextInt(10000000);
            executor.execute(new SumIt2(x, i));
        }

        //shut down the thread pool
        executor.shutdown();

        while (!executor.isTerminated()) {
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\n******Parallel time with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTime - startTime) + " milliseconds");
    }//end main
}//end class

class SumIt2 implements Runnable{
    long endValue;
    static int numId = 1;
    int threadNum;

    public SumIt2(long x, int threadNum){
        endValue = x;
        numId++;
        this.threadNum = threadNum;
    }

    @Override
    public void run(){
        long total = 0;
        for (int i = 0; i < endValue; i++){
            total = total + i;
        }
        System.out.println("Thread " + threadNum + " added up from 0 to " +
                endValue + ": "+ total);

    }
}