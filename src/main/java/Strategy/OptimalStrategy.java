package Strategy;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Player;
import placements.AttackPlacement;
import placements.Placement;

import java.util.Optional;

public class OptimalStrategy extends Strategy{
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        Placement placement = AttackPlacement.getInstance();

        while(placement != null){
            Optional<Cell> cell = placement.place(board, player);
            if(cell.isPresent()){
                return cell.get();
            }
            placement = placement.next();
        }
        return null;
    }
}
