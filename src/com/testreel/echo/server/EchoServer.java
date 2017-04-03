package com.testreel.echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket();

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e){
            System.err.println("Could not listen to port 1234");
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Waiting for connection....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e){
            System.err.println("Accept failed");
            System.exit(1);
        }

        System.out.println("Connection successful");
        System.out.println("Waiting for input....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

        String inputLine = in.readLine();
        System.out.println("Server: " + inputLine);
        out.println(inputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
