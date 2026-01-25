package api;

import Entity.boards.Board;
import Entity.boards.TicTacToeBoard;
import Entity.game.*;
import Strategy.StrategyFactory;

public class AIEngine {

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToeBoard boardInstance) {
            StrategyFactory strategyFactory = new StrategyFactory();
            Cell cell = strategyFactory.getStrategy(boardInstance,player).getOptimalMove(boardInstance,player);
            return new Move(cell,player);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
