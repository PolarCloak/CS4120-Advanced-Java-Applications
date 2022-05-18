package HW1A_1;

import java.util.ArrayList;

public class Bin {
    ArrayList<Double> objects = new ArrayList<Double>();
    double maxWeight;
    double totalWeight;

    public Bin(){
        this.maxWeight = 10;
        this.totalWeight = 0;
    }

    public Bin(double maxWeight){
        if(maxWeight > 0)
            this.maxWeight = maxWeight;
        else this.maxWeight = 10;
        this.totalWeight = 0;
    }

    public boolean addItem(double weight){
        if(weight <= maxWeight - totalWeight){
            objects.add(weight);
            totalWeight += weight;
            return true;
        }
        else if(weight>maxWeight){
            System.out.println("Error: " + weight + " is too heavy for a bin.");
            return false;
        }
        else return false;
    }

    public int getNumberOfObjects(){
        return objects.size();
    }

    public String toString(){
        return objects.toString();
    }


}
