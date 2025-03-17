/* Created by Shiva */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            //Connect to the Server running on Localhost, port 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            //Input stream to communicate with the server
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Output stream to communicate with the server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while (true) {
                //Prompt for a message
                System.out.println("Enter message as (exit) to quit: ");
                message = input.readLine();

                //Send the message to the server
                output.println(message);

                //If the message is (exit), break the loop and disconnect
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                //Read the echoed response from the server and display it
                String serverResponse = serverInput.readLine();
                System.out.println(serverResponse);
            }

            //closing resources
            input.close();
            serverInput.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
