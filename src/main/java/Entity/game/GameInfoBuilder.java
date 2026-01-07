package Entity.game;

public class GameInfoBuilder {
    private String winner;
    private boolean isOver;
    private boolean hasFork;
    private Player player;
    private int moveCount;

    // Change return type to GameInfoBuilder and return "this"
    public GameInfoBuilder setHasFork(boolean hasFork){
        this.hasFork = hasFork;
        return this;
    }

    public GameInfoBuilder setWinner(String winner){
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder setIsOver(boolean isOver){
        this.isOver = isOver;
        return this;
    }

    public GameInfoBuilder setPlayer(Player player){
        this.player = player;
        return this;
    }

    public GameInfoBuilder setMovesCount(int moveCount){
        this.moveCount = moveCount;
        return this;
    }

    public GameInfo build(){
        return new GameInfo(winner, isOver, hasFork, player, moveCount);
    }
}