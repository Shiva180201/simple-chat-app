/* Created by Shiva */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        //Using try and catch block, because the ServerSocket throws IOException if the specified port number is invalid or already in use
         try {
             //Server socket to listen for client connections on specified port 12345
             ServerSocket serverSocket = new ServerSocket(12345);
             System.out.println("Server is waiting for a client connection...");

             //Accept a client connection
             Socket clientSocket = serverSocket.accept();
             System.out.println("Client Connected.");

             //To read data from socket object in raw bytes and convert into char set
             InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
             BufferedReader input = new BufferedReader(inputStreamReader);

             //To write the output to the output stream of the socket object
             PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

             String clientMessage;
             while (true) {
                 //To read message from the client
                 clientMessage = input.readLine(); //This blocks until a line is read
                 System.out.println("Message received from client: "+clientMessage);

                 //If the client sends "exit" as message, break the loop and close the connection
                 if (clientMessage.equalsIgnoreCase("exit")) {
                     System.out.println("Client disconnected.");
                     break;
                 }

                 //Send an echo message back to the client
                 output.println("Server: "+clientMessage);
             }

             //Closing resources
             input.close();
             output.close();
             clientSocket.close();
             serverSocket.close();

         } catch (IOException e) {
             System.out.println("The specified port is invalid or already in use "+e.getMessage());
             e.printStackTrace();
         }
    }
}
