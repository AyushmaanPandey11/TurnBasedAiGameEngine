package Entity.boards;

import Entity.game.Move;

public interface Board {
    Board move(Move move);
    Board copy();
}
