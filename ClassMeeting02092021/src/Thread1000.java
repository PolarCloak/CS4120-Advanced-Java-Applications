//Threads question from in-class worksheet
/*
Write a program that creates 1000 threads.
Make a task for adding numbers from 0 up to some specified number
(like 10,000,000 or a random number in this range) and give each thread such a task.
Inside the task's run() method, print out the thread's number and the sum that it
added up. Note that adding large integers may require using long.
Sample output might look something like this:
Thread 1 added up from 0 to 494568: 122299000596
Thread 0 added up from 0 to 3617869: 6544489859515
 */

import java.util.Random;
public class Thread1000 {
    public static void main(String[] args) {
        //create an array of 1000 Threads
        Thread[] threads = new Thread[1000];

        Random rand = new Random();

        //for loop to initialize the threads
        for(int i=0; i<threads.length; i++){
            int x = rand.nextInt(10000000);
            threads[i] = new Thread(new SumIt(x,i));
        }
        //Kick of each of the threads
        for(Thread t : threads){
            t.start();
        }

    }//end main
}//end class

class SumIt implements Runnable{
    long endValue;
    int threadNum;

    public SumIt(long x, int threadNum){
        endValue = x;
        this.threadNum = threadNum;
    }

    @Override
    public  void run(){
        long total = 0;
        for (int i = 0; i < endValue; i++){
            total = total + i;
        }
        System.out.println("Thread " + threadNum + " added up from 0 to " +
                            endValue + ": "+ total);

    }
}