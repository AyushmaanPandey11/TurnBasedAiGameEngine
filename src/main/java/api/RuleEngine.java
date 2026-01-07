package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    private final HashMap<String, List<Rule<TicToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicToeBoard.class.getName(),new ArrayList<>());
        ruleMap.get(TicToeBoard.class.getName()).add(new Rule<>(ticToeBoard -> getSearchResult(ticToeBoard::getCell)));
        ruleMap.get(TicToeBoard.class.getName()).add(new Rule<>(ticToeBoard -> getSearchResult((row,col) -> ticToeBoard.getCell(col,row))));
        ruleMap.get(TicToeBoard.class.getName()).add(new Rule<>(ticToeBoard -> getDiagonalSearchResult((row) -> ticToeBoard.getCell(row,row))));
        ruleMap.get(TicToeBoard.class.getName()).add(new Rule<>(ticToeBoard -> getDiagonalSearchResult((row) -> ticToeBoard.getCell(row,2-row))));
        ruleMap.get(TicToeBoard.class.getName()).add(new Rule<>(this::gameWonOrNot));
    }

    public GameInfo getGameInfo(Board board){
        if (board instanceof TicToeBoard boardInstance){
            GameResult gameResult = isComplete(boardInstance);
            char[] players = new char[]{'X','O'};
            for (char c : players) {
                for (int outRow = 0; outRow < 3; outRow++) {
                    for (int outCol = 0; outCol < 3; outCol++) {
                        Board board1 = boardInstance.copy();
                        Player player = new Player(c, "User");
                        board1.move(new Move(new Cell(outRow, outCol), player));
                        boolean OpponentAlwaysWins = false;
                        for (int inRow = 0; inRow < 3; inRow++) {
                            for (int inCol = 0; inCol < 3; inCol++) {
                                Board board2 = board1.copy();
                                board2.move(new Move(new Cell(inRow, inCol), player.flip()));
                                if (isComplete(board2).getWinner().equals(player.flip().getPlayerName())) {
                                    OpponentAlwaysWins = true;
                                    break;
                                }
                            }
                            if (OpponentAlwaysWins) {
                                break;
                            }
                        }
                        if (OpponentAlwaysWins) {
                            return new GameInfoBuilder()
                                    .setWinner(gameResult.getWinner())
                                    .setHasFork(true)
                                    .setPlayer(player.flip())
                                    .setIsOver(gameResult.isOver())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .setWinner(gameResult.getWinner())
                    .setHasFork(false)
                    .setIsOver(gameResult.isOver())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameResult isComplete(Board board) {
        GameResult gameResult = new GameResult(false,"-");
        if(board instanceof  TicToeBoard boardInstance){
              List<Rule<TicToeBoard>> ruleList = ruleMap.get(TicToeBoard.class.getName());
              for (Rule<TicToeBoard> rule : ruleList){
                  GameResult result = rule.getRule().apply(boardInstance);
                  if (result.isOver()){
                      return result;
                  }
              }
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
