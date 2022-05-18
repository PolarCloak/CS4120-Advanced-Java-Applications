import java.sql.*;
//THis is SimpleJdbc from last class 3/4, we are adding some more queries
public class SimpleJdbc {
  public static void main(String[] args)
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
    Class.forName("com.mysql.cj.jdbc.Driver");   //1. load driver
    System.out.println("Driver loaded");

    // 2. Connect to a database
    Connection connection = DriverManager.getConnection
      ("jdbc:mysql://localhost/javabook" , "root", "root");
    System.out.println("Database connected");

    // 3. Create a statement
    Statement statement = connection.createStatement();

    // 4. Execute a statement
    ResultSet resultSet = statement.executeQuery
      ("select firstName, mi, lastName from Student where lastName "
        + " = 'Smith'");

    // 5. Iterate through the result and print the student names
    while (resultSet.next())
      System.out.println(resultSet.getString(1) + "\t" +
        resultSet.getString(2) + "\t" + resultSet.getString(3));

    //create a query to display
    //lastname, firstname, deptId of faculty who are Full Professor


    //create a query to display subjectID, courseNumber, title on table course for
    //courses with courseNumber of 1999


    //create a query to display capital and state from table statecapital

    // 6. Close the connection
    connection.close();
  }
}
