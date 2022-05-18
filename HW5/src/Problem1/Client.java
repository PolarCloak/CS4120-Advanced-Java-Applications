package Problem1;

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
    TextField subtotal;
    TextField percentage;
    static TextArea ta;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();

        subtotal = new TextField();
        percentage = new TextField();
        Button btn = new Button("Submit");

        gPane.addRow(0, new Label("Subtotal: "), subtotal);
        gPane.addRow(1, new Label("Tip Percentage (0-100): "), percentage);

        bPane.setTop(gPane);
        ta = new TextArea();
        bPane.setCenter(ta);
        bPane.setBottom(btn);

        btn.setOnAction(new submitButton());

        Scene scene = new Scene(bPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();


        try {
            //server connection
            Socket socket = new Socket("localhost", 8000);
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
            String sub = subtotal.getText().trim();
            String per = percentage.getText().trim();

            double subtotal = Double.parseDouble(sub);
            int percent = Integer.parseInt(per);

            //create thread
            OutputTask task = null;
            task = new OutputTask(toServer,subtotal,percent);
            Thread taskThread = new Thread(task);
            taskThread.start();

        }

    }


    private static class OutputTask implements Runnable {
        ObjectOutputStream toServer;

        double subtotal;
        int percent;


        public OutputTask(ObjectOutputStream toServer, double subtotal, int percent) {
            this.toServer = toServer;
            this.subtotal = subtotal;
            this.percent = percent;
        }

        public void run() {

            try {
                System.out.println("Sending: " + subtotal + " " + percent);
                toServer.writeObject(subtotal);
                toServer.writeObject(percent);
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
                double received = 0;
                try {
                    received = (double) fromServer.readObject();
                    String d2s = String.format("%.2f",received);
                    String finalReceived = "Total with tip: $" + d2s+ "\n";
                    Platform.runLater(() -> {
                        ta.appendText(finalReceived);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(received);
            }
        }
    }
}