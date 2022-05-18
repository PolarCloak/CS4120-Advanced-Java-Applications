package HW1B_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Square extends GeometricObject implements Comparable<Square>{

    private int side;

    public Square(){
        this.side = 0;
    }

    public Square(int side){
        this.side = side;
    }

    @Override
    public double getArea(){
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return this.side * 2;
    }

    @Override
    public String toString(){
        return("Square: " + this.side + " x " + this.side);
    }

    @Override
    public int compareTo(Square o) {
        if(this.side>o.side){
            return 1;
        }
        else if(this.side<o.side){
            return -1;
        }
        else return 0;
    }

    public static void main(String[] args){
        ArrayList<Square> squares = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<100; i++){
            squares.add(new Square(r.nextInt(399) + 1));
        }
        Collections.sort(squares);
        for(Square s : squares){
            System.out.println(s);
        }

    }
}
