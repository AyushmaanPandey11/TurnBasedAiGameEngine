package Entity.game;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private int lastMoveTimeStamp;
    private int maxTimePerPlayer;
    private int maxTimePerMove;

    public Game(GameConfig gameConfig, Board board, Player winner, int lastMoveTimeStamp, int maxTimePerPlayer, int maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeStamp = lastMoveTimeStamp;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }


    public Board move(Move move, int timestampInMs){
        Board updatedBoard = null;
        int timeTakenForTheMove = timestampInMs - lastMoveTimeStamp;
        move.getPlayer().setTotalTimeUsedInMs(timeTakenForTheMove);
        if (gameConfig.isTimed()){
            updatedBoard = moveForTimedGame(move,timeTakenForTheMove);
        } else {
            updatedBoard = board.move(move);
        }
        this.board = updatedBoard;
        return updatedBoard;
    }

    public Board moveForTimedGame(Move move, int timeTakenForTheMove){
        Board updatedBoard = null;
        if (gameConfig.getTimePerMove() != null){
            if(moveMadeWithinTimeLimit(timeTakenForTheMove)){
                updatedBoard = board.move(move);
            } else {
                this.winner = move.getPlayer().flip();
            }
        } else {
            if (moveMadeWithinTimeLimit(move.getPlayer())){
                updatedBoard = board.move(move);
            } else {
                this.winner = move.getPlayer().flip();
            }
        }
        return updatedBoard;
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
