package ua.gameserv.core.room;

import ua.gameserv.core.room.player.Player;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServerRoom {

    private final String roomID;
    private final ConcurrentMap<String, Player> players;

    public ServerRoom(String roomID) {
        this.roomID = roomID;
        this.players = new ConcurrentHashMap<>();
    }

    public Player addPlayer(String token, Player player) {
        if(players.size() == 2 || players.containsKey(token)) {
            return null;
        }
        players.put(token, player);
        return player;
    }

    public String getRoomID() {
        return roomID;
    }
}
