package placements;

import Entity.game.Cell;

import java.util.Optional;

public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;

    CenterPlacement(){}

    public static CenterPlacement getInstance(){
        if(centerPlacement == null){
            centerPlacement = new CenterPlacement();
        }
        return centerPlacement;
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
