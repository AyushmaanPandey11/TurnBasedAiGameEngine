package main.java.Entity.boards;

import main.java.Entity.game.Board;
import main.java.Entity.game.Grid;
import main.java.Entity.game.Move;

public class TicToeBoard extends Board {
    Grid[][] cells = new Grid[3][3];

    public Grid getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Move move, String value) {
        Grid grid = new Grid(value,move.getPlayer());
        cells[move.getCell().getRow()][move.getCell().getCol()]=grid;
    }

    @Override
    public void move(Move move){
        this.setCell(move,move.getPlayer().getValue());
    }

    @Override
    public String toString(){
        String result = "";
        for (int row=0;row<3;row++){
            for (int col=0;col<3;col++){
                if(cells[row][col] == null){
                    result += '-';
                    continue;
                }
                result += cells[row][col].getValue();
            }
            result += "\n";
        }
        return result;
    }
}
