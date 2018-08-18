package ua.gameserv.core.room.player;

import java.net.InetAddress;

public class Player {
    public final String playerID;
    public final InetAddress address;
    public final Integer health;

    public Player(String playerID, InetAddress address, Integer health) {
        this.health = health;
        this.address = address;
        this.playerID = playerID;
    }
}
