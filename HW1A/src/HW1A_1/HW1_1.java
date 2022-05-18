package HW1A_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HW1_1 {

    static ArrayList<Bin> bins = new ArrayList<>();
    static ArrayList<Double> inputValues = new ArrayList<Double>();
    private static final double MAXWEIGHT = 10;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("How many items would you like to add to bins?: ");
        int itemCount = input.nextInt();

        System.out.print("Please input your values: ");
        double[] items = new double[itemCount];
        for(int i = 0; itemCount > i; i++){
            items[i] = input.nextDouble();
        }

        //sort the array first.
        Arrays.sort(items);

        //run addItems() on the items array.
        addItems(items);

        //final output; results.
        System.out.println("Minimum number of bins: " + bins.size());
        for(int i = 1; i<= bins.size(); i++){
            System.out.print("Container "+i+" contains objects with weight " + bins.get(i-1).toString());
            System.out.println();
        }
    }

    private static void startNewBin(double startingWeight){
        Bin b = new Bin(MAXWEIGHT);
        b.addItem(startingWeight);
        bins.add(b);
    }

    private static void addItems(double[] items){
        boolean placedItem = false;
        for(int i = items.length -1; i>=0; i--){
            boolean placeItem = false;
            for(Bin bin : bins){
                if(bin.addItem(items[i])){
                    placeItem = true;
                    break;
                }
            }
            if(placeItem == false){
                startNewBin(items[i]);
                placedItem = true;
            }
        }
        return;
    }
}
