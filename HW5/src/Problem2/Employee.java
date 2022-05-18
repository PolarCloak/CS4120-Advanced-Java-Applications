package Problem2;

import java.io.Serializable;

public class Employee implements Serializable {

    long ID;
    String firstName;
    String lastName;
    double salary;

    public Employee(long ID, String firstName, String lastName, double salary){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String toString(){
        return(ID + ": " + firstName + " " + lastName + " | $" + salary);
    }



}
