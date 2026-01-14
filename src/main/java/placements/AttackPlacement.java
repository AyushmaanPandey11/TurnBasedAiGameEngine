package placements;

import Entity.game.Cell;

import java.util.Optional;

public class AttackPlacement implements Placement{
    private static AttackPlacement instance;

    AttackPlacement(){}

    public static Placement getInstance(){
        if(instance == null){
            instance = new AttackPlacement();
        }
        return instance;
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
