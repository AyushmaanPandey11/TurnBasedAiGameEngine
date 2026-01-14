package placements;

import Entity.game.Cell;

import java.util.Optional;

public interface Placement {
    Placement next();
    Optional<Cell> place();
}
