package GivenFiles;
//Various imports

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

public class ClientSkeleton extends Application {

    //Declare the variables for io stream for client to and from the server
    ObjectOutputStream toServer;
    ObjectInputStream fromServer;


    //variables for the GUI
    TextField tfname;
    TextField tfmessage;
    static TextArea ta;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();

        tfname = new TextField();
        tfmessage = new TextField();
        Button btn = new Button("Submit");

        gPane.addRow(0, new Label("Name: "), tfname);
        gPane.addRow(1, new Label("Message: "), tfmessage);

        bPane.setTop(gPane);
        ta = new TextArea();
        bPane.setCenter(new ScrollPane(ta));
        bPane.setBottom(btn);

        btn.setOnAction(new sendMessage());

        Scene scene = new Scene(bPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        //set the io stream for this client, initialize to and from
        try {
            //connect to the server, create socket on port 8000
            Socket socket = new Socket("localhost", 8030);

            // Create an output stream to send data to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());

            // Create an input stream to receive data from the server
            fromServer = new ObjectInputStream(socket.getInputStream());


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //update GUI to show progress.
        Platform.runLater(() -> {
            ta.appendText("Connected to Server\n");
        });

        //Create and initalize an instance of ReceiveTask. This
        //is the task and thread this client will receive messages.
        ReceiveTask receiveTask = new ReceiveTask(fromServer);

        //Create thread and initialize with ReceiveTask instance above
        Thread clientThread = new Thread(receiveTask);

        //start the above thread;
        clientThread.start();

    }

    //public static void main(String[] args) throws IOException {
    class sendMessage implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //get the message and sender name
            String name = tfname.getText().trim();
            String msg = tfmessage.getText().trim();

            //create threads
            //create instance of SendTask
            SendTask sendTask = null;
            sendTask = new SendTask(toServer,name,msg);

            //Create thread with SendTask instance above
            Thread sendMessageThread = new Thread(sendTask);

            //Start the thread
            sendMessageThread.start();

        }

    }


    private static class SendTask implements Runnable {
        //Declare stream to write to the server
        ObjectOutputStream toServer;
        String name;
        String msg;


        public SendTask(ObjectOutputStream toServer, String name, String msg) {
            this.toServer = toServer;
            this.name = name;
            this.msg = msg;
        }

        public void run() {

            try {
//                Platform.runLater(() -> {
//                    ta.appendText("\nSending: " + name + ": " + msg);
//                });
                System.out.println("Sending: " + name + ": " + msg);
                //write to the server, two objects.
                toServer.writeObject(name);
                toServer.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static class ReceiveTask implements Runnable {
        //Declare stream to receive data from the server
        ObjectInputStream fromServer;


        public ReceiveTask(ObjectInputStream fromServer) {
            this.fromServer = fromServer;
        }

        @Override
        public void run() {
            while (true) {
                String received = null;
                try {
                    received = (String) fromServer.readObject();
                    String finalReceived = received + "\n";
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