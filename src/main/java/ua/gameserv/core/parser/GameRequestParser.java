package ua.gameserv.core.parser;

import core.RandomNumberGenerator;
import org.apache.commons.lang.StringUtils;
import ua.gameserv.core.room.ServerRoom;
import ua.gameserv.core.room.ServerRoomService;
import ua.gameserv.core.room.player.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static ua.gameserv.core.parser.CommandConstants.DELIMITER;

public class GameRequestParser {

    private ServerRoomService serverRoomService;
    private Socket socket;
    private InputStream in;
    private PushbackInputStream pin;
    private OutputStream out;
    private BufferedReader bufferedReader;
    private final static byte[] ERROR_MSG = "Wrong request format".getBytes();

    public GameRequestParser(Socket socket) throws IOException {
        this.serverRoomService = ServerRoomService.instance();
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.pin = new PushbackInputStream(in);
        this.bufferedReader = new BufferedReader(new InputStreamReader(in));

        parse();
    }

    private void parse() throws IOException {
        while (true) {
            int firstByte = pin.read();
            pin.unread(firstByte);
            bufferedReader = new BufferedReader(new InputStreamReader(pin));
            String line = bufferedReader.readLine();
            List<String> requestList = new ArrayList<>(100);
            while (!"END".equals(line)) {
                requestList.add(line);
                line = bufferedReader.readLine();
            }

            String versionLine = requestList.get(0);
            String commandLine = requestList.get(1);
            String[] version = versionLine.split(DELIMITER);
            String[] command = commandLine.split(DELIMITER);
            if (!CommandConstants.GAME.equals(version[0])) {
                out.write(ERROR_MSG);
            }

            if (!CommandConstants.COMMAND.equals(command[0])) {
                out.write(ERROR_MSG);
            }

            CommandConstants.CommandType commandType = CommandConstants.CommandType.valueOf(command[1]);

            switch (commandType) {
                case START:
                    initStart(requestList);
                    break;
                case ACTION:
                    initMove(requestList);
                    break;
                default:
                    out.write(ERROR_MSG);
                    return;
            }
        }
    }

    private void initStart(List<String> requestList) throws IOException {
        String roomLine = requestList.get(2);
        String[] room = roomLine.split(DELIMITER);
        ServerRoom serverRoom;
        if (room.length < 2 || StringUtils.EMPTY.equals(room[1])) {
            System.out.println("Player have no room, room will be created");
            serverRoom = serverRoomService.createRoom();
        } else {
            serverRoom = serverRoomService.getRoom(room[1]);
            if (serverRoom == null) {
                System.out.println("Room not found");
                return;
            }
        }

        Player player = serverRoomService.createPlayer(socket.getInetAddress());
        if (serverRoom.addPlayer(player.playerID, player) == null) {
            System.out.println("Player can't be added");
            return;
        }

        out.write(String.format("AUTH:%s\nROOM:%s\nEND\n", player.playerID, serverRoom.getRoomID()).getBytes());
        sendToClient();
    }

    private void initMove(List<String> requestList) throws IOException {

        GameRequest gameRequest = new GameRequest();

        String roomLine = requestList.get(2);
        String[] room = roomLine.split(DELIMITER);
        String authLine = requestList.get(3);
        String[] auth = authLine.split(DELIMITER);

        String attackLine = requestList.get(4);
        String[] attack = attackLine.split(DELIMITER);
        String defenceLine =  requestList.get(5);
        String[] defence = defenceLine.split(DELIMITER);
        String dodgeLine =  requestList.get(6);
        String[] dodge = dodgeLine.split(DELIMITER);
        String recoveryLine =  requestList.get(7);
        String[] recovery = recoveryLine.split(DELIMITER);

        gameRequest.room = room[1];
        gameRequest.token = auth[1];
        gameRequest.attack = Integer.parseInt(attack[1]);
        gameRequest.defence = Integer.parseInt(defence[1]);
        gameRequest.dodge = Integer.parseInt(dodge[1]);
        gameRequest.recover = Integer.parseInt(recovery[1]);

        out.write(("Request accepted: " + gameRequest).getBytes());

    }

    public void sendToClient() {
        try {
            out.flush();
        } catch (IOException e) {
            System.out.println("Connection with client was interrupted");
            e.printStackTrace();
        }
    }

    private String[] getData() {
        return null;
    }
}
