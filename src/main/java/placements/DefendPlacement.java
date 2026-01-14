package placements;

import Entity.game.Cell;

import java.util.Optional;

public class DefendPlacement implements Placement{
    private static DefendPlacement defendPlacement;

    DefendPlacement(){}

    public static DefendPlacement getInstance(){
        if(defendPlacement == null){
            defendPlacement = new DefendPlacement();
        }
        return defendPlacement;
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
