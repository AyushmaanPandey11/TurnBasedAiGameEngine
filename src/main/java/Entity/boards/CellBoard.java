package Entity.boards;

import Entity.game.Board;
import Entity.game.Grid;

public interface CellBoard extends Board {
    Grid getCell(int row, int col);
}
