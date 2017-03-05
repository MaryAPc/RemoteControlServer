package com.maryapc.remotecontrolserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8081;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Started: " + serverSocket);
        try {
            Socket socket = serverSocket.accept();
            try {
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true) {
                    try {
                        String str = in.readLine();
                        if (str == null) {
                            break;
                        }
                        System.out.println("Echoing: " + str);
                        out.println(str);
                    } catch (NullPointerException e) {
                        break;
                    }
                }
            } finally {
                System.out.println("closing...");
                socket.close();
            }
        } finally {
            serverSocket.close();
        }
    }
}