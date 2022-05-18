package Exercise7;

public class Employee implements Comparable<Employee>{

    public String firstName;
    public String lastName;
    public double salary;

    public Employee(String firstName, String lastName, double salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        int compared = this.firstName.compareTo(o.firstName);
        if(compared!=0) return compared;
        compared = this.lastName.compareTo(o.lastName);
        if(compared!=0) return compared;
        else return 0;
    }

    @Override
    public String toString(){
        String s = "";
        s+= firstName;
        s+=" ";
        s+= lastName;
        return s;
    }
}
