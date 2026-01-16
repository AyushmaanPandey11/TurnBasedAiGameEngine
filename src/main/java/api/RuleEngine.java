package api;

import Entity.boards.TicToeBoard;
import Entity.game.*;

import java.util.HashMap;

public class RuleEngine {
    private final HashMap<Class<? extends Board>, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicToeBoard.class,TicToeBoard.getRules());
    }

    public GameResult isComplete(Board board) {
        GameResult gameResult = new GameResult(false,"-");
        if(board instanceof  TicToeBoard boardInstance){
            RuleSet ruleList = ruleMap.get(TicToeBoard.class);
            for (Rule rule : ruleList){
                GameResult result = rule.getRule().apply(boardInstance);
                if (result.isOver()){
                    return result;
                }
            }
        }
        return gameResult;
    }

    public GameInfo getGameInfo(Board board){
        if (board instanceof TicToeBoard boardInstance){
            GameResult gameResult = isComplete(boardInstance);
            char[] players = new char[]{'X','O'};
            Cell forkCell=null;
            for (char c : players) {
                for (int outRow = 0; outRow < 3; outRow++) {
                    for (int outCol = 0; outCol < 3; outCol++) {
                        Player player = new Player(c, "User");
                        Board board1 = boardInstance.move(new Move(new Cell(outRow, outCol), player));
                        boolean OpponentAlwaysWins = false;
                        for (int inRow = 0; inRow < 3; inRow++) {
                            for (int inCol = 0; inCol < 3; inCol++) {
                                forkCell = new Cell(inRow, inCol);
                                Board board2 = board1.move(new Move(forkCell, player.flip()));
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
                                    .setForkCell(forkCell)
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
}
