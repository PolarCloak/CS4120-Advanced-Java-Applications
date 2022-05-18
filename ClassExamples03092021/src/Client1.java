import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        //connect to the server, create variable socket of type Socket, localhost, port 8000

        System.out.println("Connected to Server");

        //set the io stream for this client
        //declare fromServer and toServer of the type ObjectInputStream and call
        //constructor to initialize fromServer and to Server


        //create threads
        //SendTask sendMessage = new SendTask(toServer);
        //ReceiveTask receiveMessage = new ReceiveTask(fromServer);

        //Thread sm = new Thread(sendMessage);
        //Thread rm = new Thread(receiveMessage);

        //Start the threads
        //sm.start();
        //rm.start();

    }


   /* private static class SendTask implements Runnable {
        Scanner input = new Scanner(System.in);
        ObjectOutputStream toServer;

        public SendTask(ObjectOutputStream toServer){
             this.toServer = toServer;
        }

        public void run(){
            while (true){
                String message = input.nextLine();
                try {
                    //write message to toServer, use writeObject

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
*/

  /*  private static class ReceiveTask implements Runnable{
        ObjectInputStream fromServer;

        public ReceiveTask(ObjectInputStream fromServer){
            this.fromServer = fromServer;
        }

        @Override
        public void run(){
           while (true){
                String received = null;
                try {
                    //received is assigned a string read fromServer, use readObject
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(received);
            }
        }
    }*/
}

