package ua.gameserv.core.parser;

import java.util.HashSet;
import java.util.Set;

public class CommandConstants {
    public final static String GAME = "GAME";
    public final static String COMMAND = "COMMAND";
    public final static String ATTACK = "ATTACK";
    public final static String DEFENCE = "DEFENCE";
    public final static String DODGE = "DODGE";
    public final static String RECOVERY = "RECOVERY";

    public final static Set<String> commands = new HashSet<>();

    static {
        commands.add(GAME);
        commands.add(COMMAND);
        commands.add(ATTACK);
        commands.add(DEFENCE);
        commands.add(DODGE);
        commands.add(RECOVERY);
    }

    public enum CommandType {
        START,MOVE;
    }
}
