package Entity.game;

public class Player {
    private final char value;
    private final String playerName;

    public Player(char input, String name){
        this.playerName = name;
        this.value = input;
    }

    public char getValue() {
        return value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player flip(){
        char newValue = (this.value == 'X') ? 'O' : 'X';
        String newName = this.playerName.equals("bot") ? "User" : "bot";
        return new Player(newValue, newName);
    }
}