package Entity.game;

public class Cell {
    int row;
    int col;

    static Cell[][] cells = new Cell[3][3];

    public static Cell getCell(int row, int col){
        if( cells[row][col] == null ){
            cells[row][col] = new Cell(row,col);
        }
        return cells[row][col];
    }

    private Cell( int row, int col ){
        this.col=col;
        this.row=row;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
