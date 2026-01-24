package Entity.game;

import Entity.boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerPlayer, Integer maxTimePerMove){
       return new Game(new GameConfig(maxTimePerMove != null, maxTimePerMove),new TicTacToeBoard(),null,0,maxTimePerPlayer,maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerPlayer, Integer maxTimePerMove, TicTacToeBoard board){
        return new Game(new GameConfig(maxTimePerMove != null, maxTimePerMove),board,null,0,maxTimePerPlayer,maxTimePerMove);
    }

    public Game createGame(Integer maxTimePerPlayer){
        return new Game(new GameConfig(true, null),new TicTacToeBoard(),null,0,maxTimePerPlayer,null);
    }
}
