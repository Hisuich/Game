package ua.gameserv.core.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameRequestParser {
    private InputStream in;
    private OutputStream out;
    private Stream<String> request;
    private BufferedReader bufferedReader;
    private List<String> requestList = new ArrayList<>(100);
    private GameRequest gameRequest;
    private final static byte[] ERROR_MSG = "Wrong request format".getBytes();

    public GameRequestParser(InputStream in, OutputStream out) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(in));
        this.gameRequest = new GameRequest();
        parse();
    }

    private void parse() throws IOException {
        while (true) {
            String line = bufferedReader.readLine();
            if(line == null) {
                break;
            }
            requestList.add(line);
        }

        String versionLine = requestList.get(0);
        String commandLine = requestList.get(1);
        String[] version = versionLine.split(":");
        String[] command = commandLine.split(":");
        if (!CommandConstants.GAME.equals(version[0])) {
            out.write(ERROR_MSG);
        }

        if (!CommandConstants.COMMAND.equals(command[0])) {
            out.write(ERROR_MSG);
        }

        CommandConstants.CommandType commandType = CommandConstants.CommandType.valueOf(command[1]);

        switch (commandType) {
            case START:
                initStart();
                break;
            case MOVE:
                initMove();
                break;
            default:
                out.write(ERROR_MSG);
                return;
        }

    }

    private void initMove() {
        String attackLine = requestList.get(2);
        String[] attack = attackLine.split(":");
        String defenceLine =  requestList.get(3);
        String[] defence = defenceLine.split(":");
        String dodgeLine =  requestList.get(4);
        String[] dodge = dodgeLine.split(":");
        String recoveryLine =  requestList.get(5);
        String[] recovery = recoveryLine.split(":");
        System.out.println("attack " + attack[1] + "\n" +
                "defence " + defence[1] + "\n" +
                "dodge " + dodge[1] + "\n" +
                "recovery " + recovery[1] + "\n");
    }

    private void initStart() {
    }

    private String[] getData() {
        return null;
    }
}
