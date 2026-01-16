package Entity.game;

import Entity.boards.TicToeBoard;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<Board> boards = new ArrayList<>();

    public void add(Board board){
        boards.add(boards.size()+1,board);
    }

    public Board undo(){
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1);
    }

    public Board getBoardAtMove(int moveIndex){
        for (int idx=0; idx< boards.size()- (moveIndex+1);idx++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }
}
