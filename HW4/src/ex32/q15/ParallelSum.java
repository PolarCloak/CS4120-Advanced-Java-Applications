package ex32.q15;

import ex32.q13.ParallelMergeSort;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelSum {

    public static void main(String[] args) {
        Random r = new Random();
        double[] dubs = new double[9000000];
        for(int i=0; i<dubs.length; i++){
            dubs[i] = r.nextDouble();
        }
        Date startTime1 = new Date();
        double total1 = parallelSum(dubs);
        Date endTime1 = new Date();
        double timeElapsed1 = endTime1.getTime() - startTime1.getTime();
        System.out.println("Parallel Total = " + total1 + " | Total time taken: " + timeElapsed1 + "ms");

        Date startTime2 = new Date();
        double total2 = sequentialSum(dubs);
        Date endTime2 = new Date();
        double timeElapsed2 = endTime2.getTime() - startTime2.getTime();
        System.out.println("Sequential Total = " + total2 + " | Total time taken: " + timeElapsed2 + "ms");
    }


    public static double parallelSum(double[] list){
        //Declare a variable of type RecursiveAction and  set equal to new SumTask(list);
        SumTask recursiveAction = new SumTask(list);

        //Declare a variable of type ForkJoinPool and call no-arg constructor to initialize
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //on the pool variable call invoke passing the RecursiveAction variable
        forkJoinPool.invoke(recursiveAction);

        return recursiveAction.getTotal();
    }

    public static double sequentialSum(double[] list){
        double total = 0;
        for(double d : list){
            total+=d;
        }
        return total;
    }
}

class SumTask extends RecursiveAction{
    private static final double THRESHOLD = 5000;
    private static double total = 0;
    double list[];

    public SumTask(double[] list){
        this.list=list;
    }

    @Override
    protected void compute() {
        if(list.length<THRESHOLD){
            double subTotal =0;
            for(double d : list){
                subTotal+=d;
            }
            total+=subTotal;
        }
        else{
            // Obtain the first half
            double[] firstHalf = new double[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

            // Obtain the second half
            int secondHalfLength = list.length - list.length / 2;
            double[] secondHalf =  new double[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

            // Compute the sum task on first and second
            invokeAll(new SumTask(firstHalf), new SumTask(secondHalf));
        }
    }

    public double getTotal(){
        return total;
    }
}
