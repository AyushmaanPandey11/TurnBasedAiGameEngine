package main.java.Entity.game;

import java.util.Objects;

public class Grid {
    String value;
    Player player;

    public Grid(String value, Player player){
        this.value = value;
        this.player= player;
    }

    public String getValue(){
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
