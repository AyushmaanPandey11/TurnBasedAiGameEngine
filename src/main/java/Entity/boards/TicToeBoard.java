package Entity.boards;

import Entity.game.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TicToeBoard implements Board {
    private final Grid[][] cells = new Grid[3][3];
    static RuleSet<TicToeBoard> rules = new RuleSet<>();

    public Grid getCell(int row, int col) {
        return cells[row][col];
    }


    public void setCell(Move move, char value) {
        if( cells[move.getCell().getRow()][move.getCell().getCol()] == null ){
            Grid grid = new Grid(value,move.getPlayer());
            cells[move.getCell().getRow()][move.getCell().getCol()]=grid;
        } else {
            throw new IllegalStateException();
        }
    }

    public static RuleSet<TicToeBoard> getRules(){
        rules.add(new Rule<>(ticTacToeBoard -> getSearchResult(ticTacToeBoard::getCell)));
        rules.add(new Rule<>(ticTacToeBoard -> getSearchResult((row,col) -> ticTacToeBoard.getCell(col,row))));
        rules.add(new Rule<>(ticTacToeBoard -> getDiagonalSearchResult((row) -> ticTacToeBoard.getCell(row,row))));
        rules.add(new Rule<>(ticTacToeBoard -> getDiagonalSearchResult((row) -> ticTacToeBoard.getCell(row,2-row))));
        rules.add(new Rule<>(TicToeBoard::gameWonOrNot));
        return rules;
    }

    public static GameResult gameWonOrNot(TicToeBoard board){
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

    public static GameResult getDiagonalSearchResult(Function<Integer, Grid> traversal){
        return traverse(traversal);
    }

    public static GameResult getSearchResult(BiFunction<Integer, Integer, Grid> getGrid){
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

    public static GameResult traverse(Function<Integer, Grid> traversal){
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

    @Override
    public void move(Move move){
        if(move != null){
            this.setCell(move,move.getPlayer().getValue());
        }
    }

    @Override
    public TicToeBoard copy() {
        TicToeBoard boardCopy = new TicToeBoard();
        for(int row=0; row< 3; row++){
            System.arraycopy(cells[row],0,boardCopy.cells[row],0,3);
        }
        return boardCopy;
    }

    @Override
    public String toString(){
        String result = "";
        for (int row=0;row<3;row++){
            for (int col=0;col<3;col++){
                if(cells[row][col] == null){
                    result += '-';
                    continue;
                }
                result += cells[row][col].getValue();
            }
            result += "\n";
        }
        return result;
    }
}
