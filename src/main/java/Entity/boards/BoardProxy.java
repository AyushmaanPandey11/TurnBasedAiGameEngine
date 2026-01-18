package Entity.boards;

public class BoardProxy {
    String representation;

    public BoardProxy(TicTacToeBoard board){
        representation = board.toString();
    }
}
