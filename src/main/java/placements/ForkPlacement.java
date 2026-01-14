package placements;

import Entity.game.Cell;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    ForkPlacement(){}

    public static ForkPlacement getInstance(){
        if(forkPlacement == null){
            forkPlacement = new ForkPlacement();
        }
        return forkPlacement;
    }

    @Override
    public Placement next() {
        return null;
    }

    @Override
    public Optional<Cell> place() {
        return Optional.empty();
    }
}
