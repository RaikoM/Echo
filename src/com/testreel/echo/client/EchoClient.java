package com.testreel.echo.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by raiko on 31/03/2017.
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {

        String host = new String("127.0.0.1");

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(host, 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput = stdIn.readLine();

        System.out.println("input: ");
        out.println(userInput);
        System.out.println("echo: " + in.readLine());

        out.close();
        in.close();
        stdIn.close();
        socket.close();

    }
}
