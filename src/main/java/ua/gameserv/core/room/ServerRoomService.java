package ua.gameserv.core.room;

import ua.gameserv.core.room.player.Player;

import java.net.InetAddress;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServerRoomService {
    private static ServerRoomService roomService;
    private final static ConcurrentMap<String, ServerRoom> rooms = new ConcurrentHashMap<>();

    public static ServerRoomService instance() {
        if (roomService == null) {
            synchronized (ServerRoomService.class) {
                if (roomService == null) {
                    roomService = new ServerRoomService();
                }
            }
        }
        return roomService;
    }

    public Player createPlayer(InetAddress address) {
        String playerID = UUID.randomUUID().toString();
        Player player = new Player(playerID, address, 100);
        return player;
    }

    public ServerRoom createRoom() {
        String roomID = UUID.randomUUID().toString();
        ServerRoom room = new ServerRoom(roomID);
        rooms.put(roomID, room);
        return room;
    }

    public ServerRoom getRoom(String roomID) {
        return rooms.get(roomID);
    }

}
