package Strategy;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Player;

public abstract class Strategy {
    abstract public Cell  getOptimalMove(TicTacToeBoard board, Player player);
}
