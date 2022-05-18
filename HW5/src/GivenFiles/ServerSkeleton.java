package GivenFiles;

//Various imports

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

public class ServerSkeleton extends Application {
    //static ArrayList of ClientTask -
    //will hold all the clients that have connected
    private static ArrayList<ClientTask> clientTasks = new ArrayList<>();

    //variable of type int that will keep count of clients.
    public int count = 0;

    //textarea to display information about whats
    //happening on the server.
    private static TextArea ta = new TextArea();

    public static void main(String[] args){
        launch(args);
    }

    // public static void main(String[] args) throws IOException {
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
                //create server socket on port 8000
                ServerSocket serverSocket = new ServerSocket(8030);

                //display some helpful information
                ta.appendText("Chat server started at " + new Date() + '\n');

                //wait for connections
                while (true) {
                    ta.appendText("Waiting for clients..." + '\n');

                    //listen for a connection request and accept
                    Socket cSocket = serverSocket.accept();

                    //increment number of clients
                    count++;

                    //show info that client connected.
                    Platform.runLater( () -> {
                        ta.appendText("Client " + count + " connected" + '\n');
                    });

                    //Create a ClientTask instance  name might be "Client" + count
                    ClientTask cTask = new ClientTask("Client" + count, cSocket);

                    //add ClientTask object to the ArrayList of ClientTask
                    clientTasks.add(cTask);

                    //Replace *** with name of your ClientTask object
                    Thread thread = new Thread(cTask);
                    System.out.println("thread started");
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
            //initialize property clientName
            clientName = n;

            //setup socket
            this.socket = socket;

            //setup io streams - the properties fromClient and toClient
            fromClient = new ObjectInputStream(socket.getInputStream());
            toClient = new ObjectOutputStream(socket.getOutputStream());

        }

        public void run(){
            System.out.println("Listener thread running");
            String name = "";
            String message="";

            while (true){
                //message will be concatenated with the two
                //objects sent from the client. Use readObject(), two times
                try {
                    name += (String) fromClient.readObject();
                    message += ": ";
                    message += (String) fromClient.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //output to server to see what is happening.
                ServerSkeleton.ta.appendText("Received message: " + message); // not working


                //broadcast message to all the clients
                for (ClientTask c: ServerSkeleton.clientTasks){
                    //check to see if message is from the originator
                    if (c.clientName == this.clientName) {
                        try {
                            c.toClient.writeObject("Me" + message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            c.toClient.writeObject(name + message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}//end class Server
