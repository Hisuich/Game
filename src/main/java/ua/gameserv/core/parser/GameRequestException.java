package ua.gameserv.core.parser;

public class GameRequestException extends Exception {

    private String message;

    public GameRequestException(String s) {
        super(s);
        this.message = s;
    }
}
