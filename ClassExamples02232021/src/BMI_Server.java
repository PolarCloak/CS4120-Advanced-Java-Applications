import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/*


*/
 
public class BMI_Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("BMI Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    //create a thread that connects to the client
    new Thread(() -> {
      try {
        connectToClient();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

   }

  private void connectToClient() throws IOException {
      //create a server socket
      ServerSocket serverSocket = new ServerSocket(8002);
      Socket connectToClient = serverSocket.accept();

      //create input and output streams
      DataInputStream isFromClient =
              new DataInputStream(connectToClient.getInputStream());
      DataOutputStream osToClient =
              new DataOutputStream(connectToClient.getOutputStream());

      while (true){
        //receive weight from client
        double weight = isFromClient.readDouble();

        //receive height
        double height = isFromClient.readDouble();


        //compute BMI
        BMI bmi = new BMI("", weight, height);
        String status = "BMI is " + bmi.getBMI() + ". " + bmi.getStatus();

        osToClient.writeUTF(status);


      }

  }


  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
