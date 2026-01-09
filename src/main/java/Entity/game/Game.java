package Entity.game;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private int lastMoveTimeStamp;
    private int maxTimePerPlayer;
    private int maxTimePerMove;

    public void move(Move move, int timestampInMs){
        int timeTakenForTheMove = timestampInMs - lastMoveTimeStamp;
        move.getPlayer().setTotalTimeUsedInMs(timeTakenForTheMove);
        if (gameConfig.isTimed()){
            moveForTimedGame(move,timeTakenForTheMove);
        } else {
            board.move(move);
        }
    }

    public void moveForTimedGame(Move move, int timeTakenForTheMove){
        if (gameConfig.getTimePerMove() != null){
            if(moveMadeWithinTimeLimit(timeTakenForTheMove)){
                board.move(move);
            } else {
                this.winner = move.getPlayer().flip();
            }
        } else {
            if (moveMadeWithinTimeLimit(move.getPlayer())){
                board.move(move);
            } else {
                this.winner = move.getPlayer().flip();
            }
        }
    }

    public boolean moveMadeWithinTimeLimit(int timeTakeSinceLastMove) {
        return  timeTakeSinceLastMove < this.maxTimePerMove;
    }

    public boolean moveMadeWithinTimeLimit(Player player) {
        return player.getTotalTimeUsedInMs() < this.maxTimePerPlayer;
    }

    public Player getWinner(){
        return this.winner;
    }
}
