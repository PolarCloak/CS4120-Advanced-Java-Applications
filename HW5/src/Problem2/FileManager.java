package Problem2;

import java.io.*;

public class FileManager {

    public void addToFile(Employee emp) throws IOException {
        String filepath = "Employees/" + emp.ID + ".emp";
        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(emp);
        objectOut.close();
    }

    public Employee printFromFile(){
        try {
            FileInputStream fileIn = new FileInputStream("Employees/");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Employee emp = (Employee) objectIn.readObject();
            objectIn.close();
            return emp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
