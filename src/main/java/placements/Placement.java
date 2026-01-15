package placements;

import Entity.boards.TicToeBoard;
import Entity.game.Cell;
import Entity.game.Player;
import api.RuleEngine;

import java.util.Optional;

public interface Placement {
    RuleEngine rules = new RuleEngine();
    Placement next();
    Optional<Cell> place(TicToeBoard board, Player player);
}
