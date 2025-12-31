package Entity.game;

public class GameResult {
    boolean isOver;
    String winner;

    public GameResult(boolean isWon, String winner){
        this.winner = winner;
        this.isOver= isWon;
    }

    public boolean isOver(){
        return isOver;
    }

    public String getWinner(){
        return this.winner;
    }
}
