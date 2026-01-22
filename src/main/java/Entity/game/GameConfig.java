package Entity.game;

public class GameConfig {
    private boolean isTimed;
    private final Integer TimePerMoveLimit;

    public GameConfig(boolean isTimed, Integer timePerMoveLimit) {
        this.isTimed = isTimed;
        TimePerMoveLimit = timePerMoveLimit;
    }

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
