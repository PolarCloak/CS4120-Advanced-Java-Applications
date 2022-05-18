package Exercises;


import java.util.*;

/* part 1
(Distinct elements in ArrayList) Write the following method that returns a new ArrayList.
The new list contains the non-duplicate elements from the original list.
public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
Additonally, create a main() method that exercises the removeDuplicates() method.
Specifically, create an ArrayList of one hundred integers ranging from 0 to 9, inclusive.
Display the contents of the ArrayList. Use the removeDuplicates() method on this
ArrayList. Display the contents of the new array list. Repeat these steps done for
integers, but using random characters of lowercase letters instead of integers.
Hint: recall the contains method
 */

/* part 2
(Maximum element in an array) Implement the following method that returns the
maximum element in an array:
public static <E extends Comparable<E>> E max(E[] list)
Additionally, create a main() method that exercises the max() method. Specifically,
create an array of one hundred random integers ranging from 0 to 10000, inclusive.
Display the contents of the array. Use the max() method on the array and display its
result. Next, create an array of random doubles, ranging from 0.0 to 1.0, exclusive.
Display the contents of the array. Use the max() method on the array and display its
result.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("\n ~ Exercise 19.3 ~ \n");

        ArrayList<Integer> ints = generateRandomArrayList("integer", 100);
        System.out.println("Randomly generated list: " + ints);
        ints = removeDuplicates(ints);
        System.out.println("Removed all duplicates: " + ints);
        Collections.sort(ints);
        System.out.println("Sorted: " + ints);

        System.out.println();

        ArrayList<Character> chars = generateRandomArrayList("character", 100);
        System.out.println("Randomly generated list: " + chars);
        chars = removeDuplicates(chars);
        System.out.println("Removed all duplicates: " + chars);
        Collections.sort(chars);
        System.out.println("Sorted: " + chars);



        System.out.println("\n ~ Exercise 19.5 ~ \n");

        Integer[] intArray = generateIntList(100, 10000);
        System.out.println("Randomly generated list: " + toArrayList(intArray));
        int maxInt = max(intArray);
        System.out.println("Max int: " + maxInt);

        Double[] doubleArray = generateDoubleList(100);
        System.out.println("Randomly generated list: " + toArrayList(doubleArray));
        double maxDouble = max(doubleArray);
        System.out.println("Max double: " + maxDouble);

    }

    public static <E> ArrayList<E> toArrayList(E[] array){
        ArrayList<E> list = new ArrayList<>();
        for(E e : array){
            list.add(e);
        }
        return list;
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
        ArrayList<E> list2 = new ArrayList<>();
        for(E item : list) {
            if (!list2.contains(item)) list2.add(item);
        }
        return list2;
    }



    public static ArrayList generateRandomArrayList(String type, int numberOfItems){
        Random r = new Random();
        switch (type.toLowerCase()){
            case "integer":
                ArrayList<Integer> ints = new ArrayList<>();
                for(int i=1;i<=numberOfItems; i++){
                    ints.add(r.nextInt(9+1));
                }
                return ints;

            case "character":
                ArrayList<Character> chars = new ArrayList<>();
                for(int i=1;i<=numberOfItems; i++){
                    char c = (char) ('a' + r.nextInt(26));
                    chars.add(c);
                }
                return chars;

            default: return null;

        }

    }
    public static Integer[] generateIntList(int numberOfItems, int maxValue){
        Random r = new Random();
        Integer[] ints = new Integer[numberOfItems];
        for(int i=0;i<numberOfItems; i++){
            ints[i] = (r.nextInt(maxValue+1));
        }
        return ints;
    }

    public static Double[] generateDoubleList(int numberOfItems){
        Random r = new Random();
        Double[] dubs = new Double[numberOfItems];
        for(int i=0;i<numberOfItems; i++){
            dubs[i] = (r.nextDouble());
        }
        return dubs;
    }

    public static <E extends Comparable<E>> E max(E[] list){
        E max = null;
        for(E e : list){
            if(max==null) max = e;
            if(max.compareTo(e)<1) max = e;
        }
        return max;
    }

}
