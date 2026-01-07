package Entity.game;

import java.util.function.Function;

public class Rule<T extends Board>{
    private final Function<T,GameResult> condition;

    public Rule(Function<T,GameResult> condition){
        this.condition = condition;
    }

    public Function<T,GameResult> getRule(){
        return this.condition;
    }
}
