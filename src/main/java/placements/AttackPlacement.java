package placements;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.Move;
import Entity.game.Player;
import utils.Utils;

import java.util.Optional;

public class AttackPlacement implements Placement{
    private static AttackPlacement instance;

    AttackPlacement(){}

    public synchronized static AttackPlacement getInstance(){
        instance = (AttackPlacement) Utils.getIfNull(instance, AttackPlacement::new);
        return instance;
    }

    private static Cell getVictoryCell(TicTacToeBoard board, Player player) {
        for (int row=0; row< 3; row++){
            for (int col=0;col <3; col++){
                if( board.getCell(row,col) == null){
                    Move move = new Move(Cell.getCell(row,col), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (player.getPlayerName().equals(rules.isComplete(boardCopy).getWinner())) {
                        return Cell.getCell(row,col);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Placement next() {
        return DefendPlacement.getInstance();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(getVictoryCell(board,player));
    }
}
