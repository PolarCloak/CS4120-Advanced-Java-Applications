package Exercise7;


/*
(Generic binary search) Implement the following method using binary search:
public static <E extends Comparable<E>>
 int binarySearch(E[] list, E key)
Note that Listing 7.7 on page 270 provides an implementation of binarySearch() for int.
Create a main() method that exercises the binarySearch() method as described in the
bullet points below.
Create an Employee class that comprises a string variable for the first name, a string
variable for the last name, and a double for the salary. The Employee class needs to
implement the Comparable interface (Note that Section 13.6 discusses the Comparable
interface). To exercise the binarySearch() method, in the main() method:
• Create an array of a hundred random integers in the range of 0 to 99, inclusive.
• Display the contents of the array.
• Prompt the user to enter an integer.
• Use binarySearch() to check if the integer is in the array.
• Create an array of random strings, 1 to 10 characters in length.
• Display the strings.
• Prompt the user to enter a string.
• Use binarySearch() to check if the string is in the array.
• Create an array of random Employee objects (use random strings for the first name
and last name and random numbers for the salary).
• Display the contents of the array.
• Prompt the user to enter the first name and last name to search the array with
binarySearch().
• Display the result of the search.
 */

import java.util.*;

public class Main {

    public static <Strings> void main(String[] args) {

        System.out.println("\n ~ Exercise 19.7 ~ \n");

        Scanner s = new Scanner(System.in);
        Integer[] ints = generateIntList(100,99);
        Arrays.sort(ints);
        System.out.println(toArrayList(ints));
        System.out.print("What value would you like to search for?: ");
        int keyI = s.nextInt();
        int locationI = binarySearch(ints, keyI);
        if(locationI ==-1) System.out.println("The value " + keyI + " was not found.");
        else{System.out.println("The value " + keyI + " was found at index: " + locationI + ".");}

        System.out.println();
        ArrayList<String> str = generateRandomArrayList("string",10);
        Collections.sort(str);
        System.out.println(str+"\n");
        String[] strings = toArrayString(str);
        System.out.print("What string would you like to search for?: ");
        String keyS = s.next();
        int locationS = binarySearch(strings, keyS);
        if(locationS ==-1) System.out.println("The string '" + keyS + "' was not found.");
        else{System.out.println("The string '" + keyS + "' was found at index: " + locationS + ".");}

        System.out.println();
        ArrayList<Employee> emps = generateRandomArrayList("employee",10);
        Collections.sort(emps);
        System.out.println(emps+"\n");
        Employee[] employees = toArrayEmployee(emps);
        System.out.print("What FIRST name would you like to search for?: ");
        String fn = s.next();
        System.out.print("What LAST name would you like to search for?: ");
        String ln = s.next();
        Employee keyE = new Employee(fn, ln, 0);
        int locationE = binarySearch(employees, keyE);
        if(locationE == -1) System.out.println("The employee '" + keyE + "' was not found.");
        else{System.out.println("The employee '" + keyE + "' was found at index: " + locationE + ".");}

    }

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key){
        Arrays.sort(list);
        int L = 0;
        int R = list.length-1;
        while(R!=L+1){
            int compared = key.compareTo(list[((R-L)/2)+L]);
            if(compared<0){
                R=((R-L)/2)+L;
            }
            else if(compared>0){
                L=((R-L)/2)+L;
            }
            else if(compared==0){
                return ((R-L)/2)+L;
            }
        }
        return -1;

    }

    public static Integer[] generateIntList(int numberOfItems, int maxValue){
        Random r = new Random();
        Integer[] ints = new Integer[numberOfItems];
        for(int i=0;i<numberOfItems; i++){
            ints[i] = (r.nextInt(maxValue+1));
        }
        return ints;
    }

    public static <E> ArrayList<E> toArrayList(E[] array){
        ArrayList<E> list = new ArrayList<>();
        for(E e : array){
            list.add(e);
        }
        return list;
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

            case "string":
                ArrayList<String> strings = new ArrayList<>();
                int charLength = r.nextInt(10) + 1;
                for(int i=1;i<=numberOfItems; i++){
                    ArrayList<Character> chs = generateRandomArrayList("character",charLength);
                    String s = "";
                    for(char c : chs)
                        s += c;
                    strings.add(s);
                    charLength = r.nextInt(10) + 1;
                }
                return strings;

            case "employee":
                ArrayList<Employee> emps = new ArrayList<>();
                for(int i=1;i<=numberOfItems; i++){
                    ArrayList<String> fnL = generateRandomArrayList("string",1);
                    String fn = fnL.get(0);
                    ArrayList<String> lnL = generateRandomArrayList("string",1);
                    String ln = lnL.get(0);
                    Double[] salL = generateDoubleList(1);
                    Double sal = salL[0];
                    Employee e = new Employee(fn,ln,sal*100000);
                    emps.add(e);
                }
                return emps;

            default: return null;

        }

    }

    public static String[] toArrayString(ArrayList<String> list){
        String[] array = new String[list.size()];
        for(int i =0; i<list.size();i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public static Employee[] toArrayEmployee(ArrayList<Employee> list){
        Employee[] array = new Employee[list.size()];
        for(int i =0; i<list.size();i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public static Double[] generateDoubleList(int numberOfItems){
        Random r = new Random();
        Double[] dubs = new Double[numberOfItems];
        for(int i=0;i<numberOfItems; i++){
            dubs[i] = (r.nextDouble());
        }
        return dubs;
    }




}
