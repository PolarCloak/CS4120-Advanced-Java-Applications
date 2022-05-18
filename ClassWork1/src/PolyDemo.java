import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PolyDemo {

    public static void main(String[] args){

        ArrayList<Object> myList = new ArrayList<>();

        myList.add(new Loan());
        myList.add(new Date());
        myList.add(new Circle(5));

        Scanner s = new Scanner(System.in);
        System.out.print("Please input an integer: ");
        int x = s.nextInt();
        myList.add(x);

        for (Object o : myList) {
            System.out.println(o);

        }

        printObject(myList.get(2));
    }
    public static void printObject(Object someObject){
        System.out.println("Info on the object: " + someObject);
    }
}
