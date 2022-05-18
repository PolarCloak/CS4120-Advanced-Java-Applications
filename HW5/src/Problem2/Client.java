package Problem2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {

    ObjectOutputStream toServer;
    ObjectInputStream fromServer;
    //GUI
    TextField ID;
    TextField fName;
    TextField lName;
    TextField salary;

    static TextArea ta;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();

        ID = new TextField();
        fName = new TextField();
        lName = new TextField();
        salary = new TextField();

        Button btnSubmit = new Button("Submit");

        gPane.addRow(0, new Label("ID: "), ID);
        gPane.addRow(1, new Label("First Name: "), fName);
        gPane.addRow(2, new Label("Last Name: "), lName);
        gPane.addRow(3, new Label("Salary: "), salary);

        bPane.setTop(gPane);
        ta = new TextArea();
        bPane.setCenter(ta);
        bPane.setBottom(btnSubmit);

        btnSubmit.setOnAction(new submitButton());

        Scene scene = new Scene(bPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();


        try {
            //server connection
            Socket socket = new Socket("localhost", 8050);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //task that will receive objects from the server
        InputTask receiveTask = new InputTask(fromServer);
        Thread clientThread = new Thread(receiveTask);
        clientThread.start();

    }

    class submitButton implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //get the message and sender name
            String idd = ID.getText().trim();
            String fname = fName.getText().trim();
            String lname = lName.getText().trim();
            String sal = salary.getText().trim();

            long id = Long.parseLong(idd);
            double salary = Double.parseDouble(sal);

            Employee emp = new Employee(id, fname, lname, salary);

            //create thread
            OutputTask task = null;
            task = new OutputTask(toServer, emp);
            Thread taskThread = new Thread(task);
            taskThread.start();

        }

    }

    private static class OutputTask implements Runnable {
        ObjectOutputStream toServer;

        Employee emp;

        public OutputTask(ObjectOutputStream toServer, Employee emp) {
            this.toServer = toServer;
            this.emp = emp;
        }

        public void run() {

            try {
                System.out.println("Sending: " + emp);
                toServer.writeObject(emp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static class InputTask implements Runnable {
        ObjectInputStream fromServer;


        public InputTask(ObjectInputStream fromServer) {
            this.fromServer = fromServer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String received = (String) fromServer.readObject();
                    String finalReceived = received + "\n";
                    Platform.runLater(() -> {
                        ta.appendText(finalReceived);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}