//Skeleton file for HW4 Problem 2 32.13

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    public static void main(String[] args) {

        //Declare and initialize and array of Integer
        Integer[] nums = new Integer[] { 4, 5, 3, 7, 1, 9, 3, 4, 5, 14, 4, 5 };
        //parallelMergeSort(nums);
        System.out.println(Arrays.toString(nums));

        String[] strings = new String[] { "test", "abc", "Yes", "cat", "dog", "pizza", "food" };
       // parallelMergeSort(strings);
        System.out.println(Arrays.toString(strings));
    }

    /*
    public static <***> void parallelMergeSort(*** list) {
         //Declare a variable of type RecursiveAction and  set equal to new SortTask<E>(list);

        //Declare a variable of type ForkJoinPool and call no-arg constructor to initialize

        //on the pool variable call invoke passing the RecursiveAction variable
       }
     */

    /*
    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {

        private static final long serialVersionUID = 1L;
        private final int THRESHOLD = 500;
        private ***[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD)
                java.util.Arrays.sort(list);
            else {
                // Obtain the first half
                ***[] firstHalf = (***[]) new Object[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                // Obtain the second half
                int secondHalfLength = list.length - list.length / 2;
                E[] secondHalf = (E[]) new Object[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

                // Recursively sort the two halves
                invokeAll(new SortTask<***>(firstHalf), new SortTask<***>(secondHalf));

                // Merge firstHalf with secondHalf into list
                MergeSort.merge(firstHalf, secondHalf, list);
            }
        }
    }
}

*/

/*
class MergeSort {
    //The method for sorting the numbers
    public static <***> void mergeSort(***[] list) {
        if (list.length > 1) {
            // Merge sort the first half
            ***[] firstHalf = (***[]) new Object[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            ***[] secondHalf = (***[]) new Object[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }
    */


    /** Merge two sorted lists */
    /*
    public static <***> void merge(***[] list1, ***[] list2, ***[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].compareTo(list2[current2]) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }
    */

}
