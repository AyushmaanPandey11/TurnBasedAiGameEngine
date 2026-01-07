package Entity.game;

public class GameInfo {
    private String winner;
    private boolean isOver;
    private boolean hasFork;
    private Player player;
    private int moveCount;

    public GameInfo(String winner, boolean isOver, boolean hasFork, Player player, int moveCount) {
        this.hasFork=hasFork;
        this.player=player;
        this.winner=winner;
        this.isOver=isOver;
        this.moveCount=moveCount;
    }
}
