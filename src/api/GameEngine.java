package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

public class GameEngine {
    public void move(Board board, Player player, Move move ){
        if(board instanceof TicToeBoard boardInstance){
            boardInstance.setCell(move.getCell(),player.getValue());
        }
    }

    public GameResult isComplete(Board board) {
        if(board instanceof TicToeBoard boardInstance){
            // checking in the row
            boolean rowComplete = false;
            String firstChar = "-";
            for ( int row=0; row < 3; row++ ){
                firstChar = boardInstance.getCell(row,0);
                rowComplete = firstChar != null;
                for ( int col =1; col < 3; col++ ){
                    if(firstChar != null && !firstChar.equals(boardInstance.getCell(row,col))){
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete){
                    break;
                }
            }
            if(rowComplete){
                return new GameResult(true, firstChar);
            }
            // checking in the col
            boolean colComplete = false;
            for (int col = 0; col < 3; col++ ){
                firstChar = boardInstance.getCell(0,col);
                colComplete = firstChar != null;
                for ( int row =1; row < 3; row++ ){
                    if( firstChar != null && !firstChar.equals(boardInstance.getCell(row,col))){
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete){
                    break;
                }
            }
            if(colComplete){
                return new GameResult(true, firstChar);
            }
            // checking in the diagonal
            firstChar = boardInstance.getCell(0,0);
            boolean diagComplete = firstChar != null;
            for ( int row=0; row < 3; row++ ){
                if(firstChar!= null && !firstChar.equals(boardInstance.getCell(row,row)) ){
                    diagComplete = false;
                    break;
                }
            }
            if(diagComplete){
                return new GameResult(true, firstChar);
            }
            // checking for reverse diagonal
            firstChar = boardInstance.getCell(2,0);
            boolean revdiagComplete = firstChar != null;
            for ( int row= 0; row < 3; row++ ){

                if( firstChar != null && !firstChar.equals(boardInstance.getCell(row,2-row)) ){
                    revdiagComplete = false;
                    break;
                }
            }

            if(revdiagComplete){
               return new GameResult(true, firstChar);
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

    public Move suggestMove(Board board) {
        Move suggestedMove = null;
        if (board instanceof TicToeBoard boardInstance) {
            String firstChar = String.valueOf('-');
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    firstChar = boardInstance.getCell(row, col);
                    if (firstChar == null) {
                        suggestedMove = new Move(new Cell(row, col));
                    }
                }
            }
        }
        return suggestedMove;
    }
}

