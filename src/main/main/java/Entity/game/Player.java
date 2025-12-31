package main.java.Entity.game;

public class Player {
    private final String value;
    private final String playerName;

    public Player(char input, String name){
        this.playerName = name;
        this.value = String.valueOf(input);
    }

    public String getValue() {
        return value;
    }

    public String getPlayerName() {
        return playerName;
    }
}