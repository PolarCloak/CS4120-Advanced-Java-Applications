import java.io.*;
import java.net.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BMI_Client extends Application {
  // Text field for receiving radius
  private TextField tfWeight = new TextField();
  private TextField tfHeight = new TextField();
  private Button btSubmit= new Button("Submit");

  // Text area to display contents
  private TextArea ta = new TextArea();

  // IO streams
  DataOutputStream osToServer;
  DataInputStream isFromServer;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws IOException {
    ta.setWrapText(true);

    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Weight in pounds"), 0, 0);
    gridPane.add(new Label("Height in inches"), 0, 1);
    gridPane.add(tfWeight, 1, 0);
    gridPane.add(tfHeight, 1, 1);
    gridPane.add(btSubmit, 2, 1);

    tfWeight.setAlignment(Pos.BASELINE_RIGHT);
    tfHeight.setAlignment(Pos.BASELINE_RIGHT);

    tfWeight.setPrefColumnCount(5);
    tfHeight.setPrefColumnCount(5);

    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("BMI Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btSubmit.setOnAction(e -> {
      try {
        connectToServer();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });

    try{
      //create a socket to connect to the server
      Socket connectToServer = new Socket("localhost",8002);

      //create the input stream to read data from the server
      isFromServer = new DataInputStream(connectToServer.getInputStream());

      //create an output stream to send data to the server
      osToServer = new DataOutputStream(connectToServer.getOutputStream());
    } catch (IOException e) {
      ta.appendText(e.getMessage() + "\n");
    }


  }

  public void connectToServer() throws IOException {
    //get weight
    double weight = Double.parseDouble(tfWeight.getText().trim());
    //get height
    double height = Double.parseDouble(tfHeight.getText().trim());

    //send weight to server
    osToServer.writeDouble(weight);

    //send height to server
    osToServer.writeDouble(height);

    osToServer.flush();


    ta.appendText("weight: " + weight + "\nHeight: " + height + "\n");

    //get the bmi from the server
    ta.appendText(isFromServer.readUTF());

  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
