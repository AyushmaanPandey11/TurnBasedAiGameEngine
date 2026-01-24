package Entity.game;

import api.RuleEngine;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private Integer lastMoveTimeStamp;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;
    private RuleEngine ruleEngine = new RuleEngine();

    public Game(GameConfig gameConfig, Board board, Player winner, int lastMoveTimeStamp, int maxTimePerPlayer, Integer maxTimePerMove) {
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
            this.board = updatedBoard;
        }
        if( winner == null && ruleEngine.isComplete(board).isOver() ){
            winner = move.getPlayer();
        }
        return updatedBoard;
    }

    public Board moveForTimedGame(Move move, int timeTakenForTheMove){
        Board updatedBoard = null;
        if ((gameConfig.getTimePerMoveLimitPerMove() == null || moveMadeWithinTimeLimit(timeTakenForTheMove)) && moveMadeWithinTimeLimit(move.getPlayer())){
                updatedBoard = board.move(move);
                this.board = updatedBoard;
        } else {
            this.winner = move.getPlayer().flip();
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
