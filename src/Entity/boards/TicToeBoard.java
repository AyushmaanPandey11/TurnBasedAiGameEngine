package Entity.boards;

import Entity.game.Board;
import Entity.game.Cell;

public class TicToeBoard extends Board {
    String[][] cells = new String[3][3];

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String value) {
        cells[cell.getRow()][cell.getCol()]= value;
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
                result += cells[row][col];
            }
            result += "\n";
        }
        return result;
    }
}
