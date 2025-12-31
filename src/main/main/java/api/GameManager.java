package main.java.api;

import main.java.Entity.boards.TicToeBoard;
import main.java.Entity.game.Board;
import main.java.Entity.game.GameResult;
import main.java.Entity.game.Grid;

public class GameManager {
    public GameResult isComplete(Board board) {
        if(board instanceof TicToeBoard boardInstance){
            // checking in the row
            boolean rowComplete = false;
            Grid grid = null;
            for ( int row=0; row < 3; row++ ){
                grid = boardInstance.getCell(row,0);
                rowComplete = grid != null;
                for ( int col =1; col < 3; col++ ){
                    if(grid!= null && !(grid).equals(boardInstance.getCell(row,col))){
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete){
                    break;
                }
            }
            if(rowComplete){
                return new GameResult(true, grid.getPlayer().getPlayerName());
            }
            // checking in the col
            boolean colComplete = false;
            for (int col = 0; col < 3; col++ ){
                grid = boardInstance.getCell(0,col);
                colComplete = grid != null;
                for ( int row =1; row < 3; row++ ){
                    if( grid != null && !grid.equals(boardInstance.getCell(row,col))){
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete){
                    break;
                }
            }
            if(colComplete){
                return new GameResult(true, grid.getPlayer().getPlayerName());
            }
            // checking in the diagonal
            grid = boardInstance.getCell(0,0);
            boolean diagComplete = grid != null;
            for ( int row=0; row < 3; row++ ){
                if(grid != null && !grid.equals(boardInstance.getCell(row,row)) ){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete){
                return new GameResult(true, grid.getPlayer().getPlayerName());
            }
            // checking for reverse diagonal
            grid = boardInstance.getCell(2,0);
            boolean revdiagComplete = grid != null;
            for ( int row= 0; row < 3; row++ ){

                if( grid != null && !grid.equals(boardInstance.getCell(row,2-row)) ){
                    revdiagComplete = false;
                    break;
                }
            }

            if(revdiagComplete){
                return new GameResult(true, grid.getPlayer().getPlayerName());
            }

            // game not won or incomplete
            int countOfFilledCells = 0;
            for ( int row = 0; row < 3; row++ ){
                for (int col =0; col < 3; col++){
                    if(((TicToeBoard) board).getCell(row,col) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){
                return new GameResult(true,"-");
            }
        }
        return new GameResult(false,"-");
    }
}
