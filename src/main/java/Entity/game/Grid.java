package Entity.game;

import java.util.Objects;

public class Grid {
    char value;
    Player player;

    public Grid(char value, Player player){
        this.value = value;
        this.player= player;
    }

    public char getValue(){
        return this.value;
    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return Objects.equals(value, grid.value);
    }
}
