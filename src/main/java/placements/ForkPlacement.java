package placements;

import Entity.boards.TicTacToeBoard;
import Entity.game.Cell;
import Entity.game.GameInfo;
import Entity.game.Player;
import utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    ForkPlacement(){}

    public synchronized static ForkPlacement getInstance(){
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement,ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Placement next() {
        return CenterPlacement.getInstance();
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell cell = null;
        GameInfo gameInfo = rules.getGameInfo(board);
        if(gameInfo.hasFork()){
            cell = gameInfo.getForkCell();
        }
        return Optional.ofNullable(cell);
    }
}
