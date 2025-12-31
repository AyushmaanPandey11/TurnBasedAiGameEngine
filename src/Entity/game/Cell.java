package Entity.game;

public class Cell {
    int row;
    int col;


    public Cell( int row, int col ){
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
