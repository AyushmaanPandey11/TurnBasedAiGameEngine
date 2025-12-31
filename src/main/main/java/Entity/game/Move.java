package main.java.Entity.game;

public class Move {
    private Player player = null;
    private Cell cell = null;

    public Move(Cell cell, Player player ){
        this.cell = cell;
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Cell getCell(){
        return cell;
    }
}
