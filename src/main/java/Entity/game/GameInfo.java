package Entity.game;

public class GameInfo {
    private String winner;
    private boolean isOver;
    private boolean hasFork;
    private Player player;
    private int moveCount;
    private Cell forkCell;

    public GameInfo(String winner, boolean isOver, boolean hasFork, Player player, int moveCount, Cell forkCell) {
        this.hasFork=hasFork;
        this.player=player;
        this.winner=winner;
        this.isOver=isOver;
        this.moveCount=moveCount;
        this.forkCell=forkCell;
    }

    public boolean hasFork(){
        return this.hasFork;
    }

    public Cell getForkCell(){
        return this.forkCell;
    }
}
