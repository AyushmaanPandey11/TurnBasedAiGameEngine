package Entity.game;

public class GameConfig {
    private boolean isTimed;
    private Integer TimePerMoveLimit;
    public void setTimed(boolean timed) {
        this.isTimed = timed;
    }

    public boolean isTimed(){
        return this.isTimed;
    }

    public Integer getTimePerMove() {
        return this.TimePerMoveLimit;
    }
}
