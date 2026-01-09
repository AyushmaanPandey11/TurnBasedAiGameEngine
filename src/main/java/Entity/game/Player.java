package Entity.game;

public class Player {
    private User user;
    private int totalTimeUsedInMs;
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

    public int getTotalTimeUsedInMs(){
        return this.totalTimeUsedInMs;
    }

    public void setTotalTimeUsedInMs(int timeUsedInMs){
        this.totalTimeUsedInMs += timeUsedInMs;
    }
}