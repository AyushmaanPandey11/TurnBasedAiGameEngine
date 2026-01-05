package api;

import Entity.boards.TicToeBoard;
import Entity.game.Board;
import Entity.game.GameResult;
import Entity.game.Grid;

import java.util.function.BiFunction;
import java.util.function.Function;

public class GameManager {
    public GameResult isComplete(Board board) {
        GameResult gameResult = new GameResult(false,"-");
        if(board instanceof  TicToeBoard boardInstance){
            BiFunction<Integer,Integer,Grid> getNextRow = boardInstance::getCell;
            BiFunction<Integer,Integer,Grid> getNextCol = (row,col) -> boardInstance.getCell(col,row);
            // for diagonals
            Function<Integer,Grid> getNextDiag = (row) -> boardInstance.getCell(row,row);
            Function<Integer,Grid> getNextRevDiag = (row) -> boardInstance.getCell(row,2-row);

            // checking in the row
            gameResult = getSearchResult(getNextRow);
            if(gameResult.isOver()) return gameResult;

            // checking in the col
            gameResult = getSearchResult(getNextCol);
            if(gameResult.isOver()) return gameResult;

            // checking in the diagonal
            gameResult = getDiagonalSearchResult(getNextDiag);
            if(gameResult.isOver()) return gameResult;

            // checking for reverse diagonal
            gameResult = getDiagonalSearchResult(getNextRevDiag);
            if(gameResult.isOver()) return gameResult;

            // game not won or incomplete
            gameResult = gameWonOrNot(boardInstance);
            if(gameResult.isOver()) return gameResult;
        }
        return gameResult;
    }

    public GameResult gameWonOrNot(TicToeBoard board){
        GameResult gameResult = new GameResult(false,"-");
        int countOfFilledCells = 0;
        for ( int row = 0; row < 3; row++ ){
            for (int col =0; col < 3; col++){
                if((board).getCell(row,col) != null){
                    countOfFilledCells++;
                }
            }
        }
        if(countOfFilledCells == 9){
            gameResult = new GameResult(true,"-");
        }
        return gameResult;
    }

    public GameResult getDiagonalSearchResult(Function<Integer,Grid> traversal){
        return traverse(traversal);
    }

    public GameResult getSearchResult(BiFunction<Integer, Integer, Grid> getGrid  ){
        GameResult gameResult = new GameResult(false,"-");
        for ( int row=0; row < 3; row++ ){
            final int finalRow = row;
            Function<Integer,Grid> traversal = (index) -> getGrid.apply(finalRow,index);
            gameResult = traverse(traversal);
            if(gameResult.isOver()) {
                break;
            }
        }
        return gameResult;
    }

    public GameResult traverse(Function<Integer,Grid> traversal){
        GameResult gameResult = new GameResult(false,"-");
        boolean isWinningStreak = true;
        for ( int col =0; col < 3; col++ ){
            if(traversal.apply(col) == null || !traversal.apply(0).equals(traversal.apply(col))){
                isWinningStreak = false;
                break;
            }
        }
        if(isWinningStreak){
            gameResult = new GameResult(true, traversal.apply(0).getPlayer().getPlayerName());
        }
        return gameResult;
    }
}
