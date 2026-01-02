package Entity.boards;

import Entity.game.Board;
import Entity.game.Grid;
import Entity.game.Move;

public class TicToeBoard implements Board {
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
        if( cells[move.getCell().getRow()][move.getCell().getCol()] == null ){
            this.setCell(move,move.getPlayer().getValue());
        }
    }

    @Override
    public TicToeBoard copy() {
        TicToeBoard boardCopy = new TicToeBoard();
        for(int row=0; row< 3; row++){
            System.arraycopy(cells[row],0,boardCopy.cells[row],0,3);
        }
        return boardCopy;
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
