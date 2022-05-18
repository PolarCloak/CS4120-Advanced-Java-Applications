package HW1B_2;

import HW1B_1.Square;

import java.util.ArrayList;
import java.util.Collections;

public class Name implements Comparable<Name>{

    private String first;
    private String last;

    public Name(){
        this.first = "John";
        this.last = "Doe";
    }

    public Name(String first){
        this.first = first;
        this.last = "Doe";
    }

    public Name(String first, String last){
        this.first = first;
        this.last = last;
    }

    public String getFirst(){
        return this.first;
    }

    public String getLast(){
        return this.last;
    }

    @Override
    public String toString(){
        return(this.first + " " + this.last);
    }

    @Override
    public int compareTo(Name o) {
        int l = this.first.compareTo(o.first);
        if(l > 0) return 1;
        else if(l < 0) return -1;
        else{
            int f = this.last.compareTo(o.last);
            if(f > 0) return 1;
            else if(f < 0) return -1;
            else return 0;
        }
    }

    public static void main(String[] args){
        ArrayList<Name> names = new ArrayList<>();

        names.add(new Name("Samantha", "Guest"));
        names.add(new Name("David", "Guest"));
        names.add(new Name("Mark", "Potts"));
        names.add(new Name("George", "Lucas"));
        names.add(new Name("Charlie", "Hayes"));
        names.add(new Name("Kyle", "Holland"));
        names.add(new Name("Andrew", "Cooper"));
        names.add(new Name("Lian", "Hunter"));
        names.add(new Name("Ben", "Wilson"));
        names.add(new Name("Brandon", "Terry"));
        names.add(new Name("Albert", "Parsons"));
        names.add(new Name("Jason", "Doyle"));
        names.add(new Name("Raul", "Hoffman"));
        names.add(new Name("Skylar", "Harris"));
        names.add(new Name("Harley", "Barrett"));
        names.add(new Name("Skylar", "Brown"));
        names.add(new Name("Harley", "Roberts"));

        Collections.sort(names);
        for(Name n : names){
            System.out.println(n);
        }

    }
}
