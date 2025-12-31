package Entity.game;

public class Move {
    private Cell cell = null;

    public Move(Cell cell ){
        this.cell = cell;
    }


    public Cell getCell(){
        return cell;
    }
}
