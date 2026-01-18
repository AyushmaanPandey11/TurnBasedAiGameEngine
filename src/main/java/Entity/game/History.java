package Entity.game;

import Entity.boards.BoardProxy;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<BoardProxy> boards = new ArrayList<>();

    public void add(BoardProxy proxy){
        boards.add(proxy);
    }

    public BoardProxy undo(){
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1);
    }

    public List<BoardProxy> getBoards(){
        return boards.isEmpty() ? null : boards;
    }

    public BoardProxy getBoardAtMove(int moveIndex){
        moveIndex = moveIndex -1;
        for (int idx=0; idx< boards.size()- (moveIndex+1);idx++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }
}
