package Entity.game;

import Entity.boards.CellBoard;

import java.util.function.Function;

public class Rule{
    private final Function<CellBoard,GameResult> condition;

    public Rule(Function<CellBoard,GameResult> condition){
        this.condition = condition;
    }

    public Function<CellBoard,GameResult> getRule(){
        return this.condition;
    }
}
