package Strategy;

import Entity.boards.TicTacToeBoard;
import Entity.game.Player;
import api.RuleEngine;


public class StrategyFactory {
    RuleEngine rules = new RuleEngine();

    public Strategy getStrategy(TicTacToeBoard boardInstance, Player player){
        int limit = 3;
        Strategy strategy = null;
        if (cellsCount(boardInstance) >= limit) {
            strategy = new SmartStrategy();
        } else if ( cellsCount(boardInstance) +1 >= limit  ){
            strategy = new OptimalStrategy();
        } else {
            strategy = new BasicStrategy();
        }
        return strategy;
    }

    private int cellsCount(TicTacToeBoard board){
        int count =0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row, col) != null) {
                    count = count+1;
                }
            }
        }
        return count;
    }

}
