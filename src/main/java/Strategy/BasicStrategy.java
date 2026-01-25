package Strategy;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Player;

public class BasicStrategy extends Strategy{
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row, col) == null) {
                    return Cell.getCell(row,col);
                }
            }
        }
        return null;
    }
}
