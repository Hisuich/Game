package ua.gameserv.core;

import ua.gameserv.core.parser.GameRequestParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TCPServer {
    private ServerSocket serverSocket;
    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening at port: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> responseToClient(socket, serverSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void responseToClient(Socket socket, ServerSocket serverSocket) {
        try {
            System.out.println("Accepted connection: " + socket);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            GameRequestParser parser = new GameRequestParser(in, out);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
