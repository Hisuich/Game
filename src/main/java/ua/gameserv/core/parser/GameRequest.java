package ua.gameserv.core.parser;

public class GameRequest {
    public String version;
    public String command;
    public String token;
    public String room;

    public int attack;
    public int defence;
    public int dodge;
    public int recover;

    public String toString() {
        return  "room " + room + "\n" +
                "auth " + token + "\n" +
                "attack " + attack + "\n" +
                "defence " + defence + "\n" +
                "dodge " + dodge + "\n" +
                "recovery " + recover + "\n";
    }
}
