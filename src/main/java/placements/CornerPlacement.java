package placements;

import Entity.boards.TicToeBoard;
import Entity.game.Cell;
import Entity.game.Player;
import utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;

    CornerPlacement(){}

    public synchronized static CornerPlacement getInstance(){
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement,CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Placement next() {
        return null;
    }

    private static Cell getCornerCell(TicToeBoard board){
        final int[][] corners = new int[][]{{0,0},{2,0},{0,2},{2,2}};
        for (int index=0;index < 4; index++){
            if (board.getCell(corners[index][0],corners[index][1]) == null){
                return new Cell(corners[index][0],corners[index][1]);
            }
        }
        return null;
    }

    @Override
    public Optional<Cell> place(TicToeBoard board, Player player) {
        return Optional.ofNullable(getCornerCell(board));
    }
}
