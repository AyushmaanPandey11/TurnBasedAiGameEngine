package placements;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Player;
import utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;

    CenterPlacement(){}

    public synchronized static Placement getInstance(){
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Placement next() {
        return CornerPlacement.getInstance();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell cell = null;
        if( board.getCell(1,1) == null ){
            cell = new Cell(1,1);
        }
        return Optional.ofNullable(cell);
    }
}
