package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

public class AIEngine {
    public Move suggestMove(Board board, Player player) {
        Move suggestedMove = null;
        if (board instanceof TicToeBoard boardInstance) {
            Grid grid = null;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    grid = boardInstance.getCell(row, col);
                    if (grid == null) {
                        suggestedMove = new Move(new Cell(row, col),player);
                    }
                }
            }
        }
        return suggestedMove;
    }
}
