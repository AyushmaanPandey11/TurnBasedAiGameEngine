package api;

import Entity.boards.TicToeBoard;
import Entity.game.Board;
import Entity.game.GameResult;
import Entity.game.Grid;

import java.util.function.BiFunction;
import java.util.function.Function;

public class GameManager {
    public GameResult isComplete(Board board) {
        if(board instanceof  TicToeBoard boardInstance){
            BiFunction<Integer,Integer,Grid> getNextRow = boardInstance::getCell;
            BiFunction<Integer,Integer,Grid> getNextCol = (row,col) -> boardInstance.getCell(col,row);

            Function<Integer,Grid> getNextDiag = (row) -> boardInstance.getCell(row,row);
            Function<Integer,Grid> getNextRevDiag = (row) -> boardInstance.getCell(row,2-row);

            // checking in the row
            GameResult gameResultRow = getSearchResult(getNextRow);
            if(gameResultRow != null) return gameResultRow;

            // checking in the col
            GameResult gameResultCol = getSearchResult(getNextCol);
            if(gameResultCol != null) return gameResultCol;

            // checking in the diagonal
            GameResult gameResultDiag = getDiagonalSearchResult(getNextDiag);
            if(gameResultDiag != null) return gameResultDiag;

            // checking for reverse diagonal
            GameResult gameResultRevDiag = getDiagonalSearchResult(getNextRevDiag);
            if(gameResultRevDiag != null) return gameResultRevDiag;

            // game not won or incomplete
            GameResult gameNotWon = gameWonOrNot(boardInstance);
            if(gameNotWon != null) return gameNotWon;


        }
        return new GameResult(false,"-");
    }

    public GameResult gameWonOrNot(TicToeBoard board){
        int countOfFilledCells = 0;
        for ( int row = 0; row < 3; row++ ){
            for (int col =0; col < 3; col++){
                if((board).getCell(row,col) != null){
                    countOfFilledCells++;
                }
            }
        }
        if(countOfFilledCells == 9){
            return new GameResult(true,"-");
        }
        return null;
    }

    public GameResult getDiagonalSearchResult(Function<Integer,Grid> getGrid){
        boolean diagComplete = true;
        for ( int row=0; row < 3; row++ ){
            if(getGrid.apply(row) == null || !getGrid.apply(0).equals(getGrid.apply(row)) ){
                diagComplete = false;
                break;
            }
        }
        if(diagComplete){
            return new GameResult(true, getGrid.apply(0).getPlayer().getPlayerName());
        }
        return null;
    }

    public GameResult getSearchResult(BiFunction<Integer, Integer, Grid> getGrid  ){
        for ( int row=0; row < 3; row++ ){
            boolean isWinningStreak = true;
            for ( int col =0; col < 3; col++ ){
                if(getGrid.apply(row,0) == null || !(getGrid.apply(row,0).equals(getGrid.apply(row,col)))){
                    isWinningStreak = false;
                    break;
                }
            }
            if(isWinningStreak){
                return new GameResult(true, getGrid.apply(row,0).getPlayer().getPlayerName());
            }
        }
        return null;
    }
}
