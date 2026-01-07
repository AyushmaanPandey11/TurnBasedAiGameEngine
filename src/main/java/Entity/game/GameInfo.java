package Entity.game;

public class GameInfo {
    private GameResult gameResult;
    private boolean hasFork;
    private Player player;

    public GameInfo(GameResult gameResult, boolean hasFork, Player winner){
        this.gameResult= gameResult;
        this.hasFork= hasFork;
        this.player= winner;
    }

    public boolean containsFork(){
        return this.hasFork;
    }
}
