package Entity.game;

import java.util.*;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    Set<Rule<T>> ruleList = new HashSet<>();

    public void add(Rule<T> boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return ruleList.iterator();
    }
}
