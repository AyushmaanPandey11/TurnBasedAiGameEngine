package placements;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Player;
import api.RuleEngine;

import java.util.Optional;

public interface Placement {
    RuleEngine rules = new RuleEngine();
    Placement next();
    Optional<Cell> place(TicTacToeBoard board, Player player);
}
