package ua.gameserv;

import ua.gameserv.core.TCPServer;

public class Main {

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start(8707);
    }
}
