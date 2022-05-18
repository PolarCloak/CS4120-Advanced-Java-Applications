package Problem1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Server extends Application {
    //variables used by the server
    private static ArrayList<ClientTask> clientTasks = new ArrayList<>();
    public static int clientCount = 0;
    private static TextArea ta = new TextArea();

    public static void main(String[] args){
        launch(args);
    }


    public void start(Stage primaryStage){
        //set up Scene with ta
        //set up stage and add scene
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        new Thread ( () ->{
            try {
                Thread.sleep(500);
                ServerSocket serverSocket = new ServerSocket(8000);
                ta.appendText("Server started at " + new Date() + '\n');

                //wait for connections
                while (true) {
                    ta.appendText("Waiting for clients..." + '\n');

                    //listen for a connection request and accept
                    Socket cSocket = serverSocket.accept();

                    //increment number of clients when someone connects
                    clientCount++;

                    //show info that client connected.
                    Platform.runLater( () -> {
                        ta.appendText("Client " + clientCount + " connected" + '\n');
                    });

                    //Create a ClientTask instance  name might be "Client" + count
                    ClientTask cTask = new ClientTask("Client" + clientCount, cSocket);

                    //add ClientTask object to the ArrayList of ClientTask
                    clientTasks.add(cTask);

                    //Replace *** with name of your ClientTask object
                    Thread thread = new Thread(cTask);
                    thread.start();
                }//while true
            }//end try
            catch(IOException | InterruptedException ex) {
                System.out.println(ex);
            }
        }).start();

    }//end start

    //class ClientTask implement Runnable
    public static class ClientTask implements Runnable{
        String clientName;
        private Socket socket;
        private ObjectInputStream fromClient;
        private ObjectOutputStream toClient;

        public ClientTask(String n, Socket socket) throws IOException {
            clientName = n;
            this.socket = socket;
            fromClient = new ObjectInputStream(socket.getInputStream());
            toClient = new ObjectOutputStream(socket.getOutputStream());
        }

        public void run(){
            //What are you going to do on this client?
            while (true){
                double subtotal = 0;
                int tipRate = 0;

                try {
                    subtotal = (double) fromClient.readObject();
                    tipRate = (int) fromClient.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //Do here.
                double newSubtotal = subtotal + ((subtotal * tipRate) / 100);

                try {
                    toClient.writeObject(newSubtotal);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}//end class Server
