package placements;

import Entity.boards.TicToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import utils.Utils;

import java.util.Optional;

public class DefendPlacement implements Placement{
    private static DefendPlacement defendPlacement;

    DefendPlacement(){}

    public synchronized static DefendPlacement getInstance(){
        defendPlacement = (DefendPlacement) Utils.getIfNull(defendPlacement,DefendPlacement::new);
        return defendPlacement;
    }

    private static Cell getDefendingCell(TicToeBoard board, Player opponent) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(new Cell(row,col), opponent);
                    TicToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (opponent.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return new Cell(row, col);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Placement next() {
        return ForkPlacement.getInstance();
    }

    @Override
    public Optional<Cell> place(TicToeBoard board, Player player) {
        return Optional.ofNullable(getDefendingCell(board,player));
    }
}
