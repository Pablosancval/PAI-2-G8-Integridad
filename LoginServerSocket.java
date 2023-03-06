import java.io.*;
import java.net.*;

import javax.net.ServerSocketFactory;

public class LoginServerSocket {
private static final String CORRECT_USER_NAME = "meh";
private static final String CORRECT_PASSWORD = "1234";
private static final String HELLO_MESSAGE = "hola";

/**
* @param args
* @throws IOException
* @throws InterruptedException
*/
    public static void main(String[] args) throws IOException,
    InterruptedException {

    // wait for client connection and check login information

    ServerSocketFactory socketFactory = (ServerSocketFactory)
    ServerSocketFactory.getDefault();

    // create Socket from factory
    
    ServerSocket serverSocket = (ServerSocket)
    socketFactory.createServerSocket(7070);
        while (true) {
            try {
                System.err.println("Waiting for connection...");
                Socket socket = serverSocket.accept();

                // open BufferedReader for reading data from client

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // open PrintWriter for writing data to client

                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                String userName = input.readLine();
                String password = input.readLine();
                String message = input.readLine();

                if (userName.equals(CORRECT_USER_NAME) && password.equals(CORRECT_PASSWORD)){
                    if(message.toLowerCase().equals(HELLO_MESSAGE)){
                        output.println("Hello, " + userName);
                    } else {
                        output.println("Message was not a welcoming one >:(");
                    }
                } else {
                    output.println("Login Failed.");
                }
                output.close();
                input.close();
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } // end while
    }
    //serverSocket.close();
}