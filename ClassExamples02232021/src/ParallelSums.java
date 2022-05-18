//Skeleton file for HW4 Problem 3 32.15

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSums {
    public static void main(String[] args) {

        final int N = 9000000;
        // Create an array, called list, of double size N

        //loop thru list and intialize each array element to 1 or some random number.

        //Start the timer for parallelSum
        long startTime = System.currentTimeMillis();
        //System.out.println("\nThe sum using parallel sum is " + parallelSum(list));
        long endTime = System.currentTimeMillis();
        System.out.println("The number of processors is " +
                Runtime.getRuntime().availableProcessors());
        System.out.println("Time is " + (endTime - startTime)
                + " milliseconds");

        //Start the timer for the sequential sum
        startTime = System.currentTimeMillis();
        //System.out.println("\n\n\nThe sum using sequential sum is " + sequentialSum(list));
        endTime = System.currentTimeMillis();
        System.out.println("Time is " + (endTime - startTime)
                + " milliseconds");

    }

    public static double sequentialSum(double[] list){
        //declare a variable, total to keep count and intialize to 0

        //loop thru list adding each element to total.


        //return total
        return 0;  //delete this line

    }


    public static double parallelSum(double[] list) {
        //declare a variable, task to be of the type RecursiveTask to work on Double
        //and initialize to new SumTask(list, 0, list.length);

        //declare a variable of type ForkJoinPool and call no-arg constructor to initialize

        //return variable above while invoking the task declared above
       // return pool.invoke(task);

        return 0;  //get rid of this line
    }

    /* SumTask needs to extend RecursiveTask of Double
    private static class SumTask **** {

        private final static int THRESHOLD = 1000;
        private double[] list;
        private int low;
        private int high;

        public SumTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        public Double compute() {
            if (high - low < THRESHOLD) {
                double sum = 0;
                for (int i = low; i < high; i++)
                    sum += list[i];
                return new Double(sum);
            }
            else {
                int mid = (low + high) / 2;
                **** left = new SumTask(list, low, mid);
                **** right = new SumTask(list, mid, high);

                //fork right
                //fork left

                return new Double(left.join().intValue() +
                        right.join().intValue());
            }
        }
    }
    */
}
