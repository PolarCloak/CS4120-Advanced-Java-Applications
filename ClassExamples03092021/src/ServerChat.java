import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat {
    //list of all the clients that have connected
    //declare a static ArrayList of ClientTask call clientList and call constructor to create the ArrayList

   /* public static void main(String[] args) throws IOException {

        //create server socket
        //declare and initialize serverSocket of type ServerSocket, call constructor, port 8000


        //keep count of number of connections
        int count = 1;

        //declare socket
        Socket socket;

        //wait for connections
        while (true){
            System.out.println("Waiting for client...");
            //HAve the socket accept requests from serverSocket

            System.out.println("Client "+ count + " connected");

            //setup io streams
            //Declare and initalize toClient and fromClient of the type ObjectOutputStream
            //and call the constructor to initialize


            //create a task for this client and add to list
            ClientTask task = new ClientTask("client"+count, toClient, fromClient);
            clientList.add(task);  //add the new client to the arrayList clientList
            count++;

            //create thread and launch
            Thread thread = new Thread(task);
            thread.start();
        }

    }//end main
*/
 /*   public static class ClientTask implements Runnable{
        String clientName;
        private ObjectInputStream fromClient;
        private ObjectOutputStream toClient;

        public ClientTask(String n, ObjectOutputStream toClient, ObjectInputStream fromClient){
            clientName = n;
            this.fromClient = fromClient;
            this.toClient = toClient;
        }

        public void run(){
            String message="";

            while (true){
                try {
                    //assign message a casting to String of an object read from fromClient, use readObject

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(clientName + ": " + message);

                //broadcast message to all the clients
                for (ClientTask c: ServerChat.clientList){

                    if (c.clientName == this.clientName) {
                        try {
                            c.toClient.writeObject("Me: " + message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            c.toClient.writeObject(this.clientName + ": " + message + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }*/
}
