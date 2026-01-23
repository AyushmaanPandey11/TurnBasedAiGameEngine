package api;

import Entity.boards.CellBoard;
import Entity.boards.TicTacToeBoard;
import Entity.game.*;
import placements.AttackPlacement;
import placements.DefendPlacement;

import java.util.HashMap;
import java.util.Optional;

public class RuleEngine {
    private final HashMap<Class<? extends Board>, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacToeBoard.class, TicTacToeBoard.getRules());
    }

    public GameResult isComplete(Board board) {
        GameResult gameResult = new GameResult(false,"-");
        if(board instanceof  TicTacToeBoard boardInstance){
            RuleSet ruleList = ruleMap.get(TicTacToeBoard.class);
            for (Rule rule : ruleList){
                GameResult result = rule.getRule().apply(boardInstance);
                if (result.isOver()){
                    return result;
                }
            }
        }
        return gameResult;
    }
    
    public GameInfo getGameInfo(CellBoard board){
        if (board instanceof TicTacToeBoard boardInstance){
            GameResult gameResult = isComplete(boardInstance);
            for (TicTacToeBoard.Symbol symbol : TicTacToeBoard.Symbol.values()) {
                Player player = new Player(symbol.getMarker(), "User");
                for (int outRow = 0; outRow < 3; outRow++) {
                    for (int outCol = 0; outCol < 3; outCol++) {
                        if(boardInstance.getCell(outRow,outCol) == null){
                            boardInstance.move(new Move(Cell.getCell(outRow,outCol), player));
                            // simulating opponent to make defensive move
                            DefendPlacement defendPlacement = DefendPlacement.getInstance();
                            Optional<Cell> defendingCell = defendPlacement.place(boardInstance,player.flip());
                            if(defendingCell.isPresent()){
                                boardInstance.move(new Move(defendingCell.get(),player.flip()));
                                // after making defensive move by opponent, check if we can win by making attacking move
                                AttackPlacement attackPlacement = AttackPlacement.getInstance();
                                Optional<Cell> attackingCell = attackPlacement.place(boardInstance,player);
                                if (attackingCell.isPresent()){
                                    return new GameInfoBuilder()
                                            .setWinner(gameResult.getWinner())
                                            .setHasFork(true)
                                            .setPlayer(player.flip())
                                            .setForkCell(Cell.getCell(outRow,outCol))
                                            .setIsOver(gameResult.isOver())
                                            .build();
                                }
                            }
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
