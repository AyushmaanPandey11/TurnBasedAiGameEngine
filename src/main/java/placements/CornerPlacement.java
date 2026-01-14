package placements;

import Entity.game.Cell;

import java.util.Optional;

public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;

    CornerPlacement(){}

    public static CornerPlacement getInstance(){
        if(cornerPlacement == null){
            cornerPlacement = new CornerPlacement();
        }
        return cornerPlacement;
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
