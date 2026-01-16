package Entity.game;

import java.util.*;

public class RuleSet implements Iterable<Rule> {
    Set<Rule> ruleList = new HashSet<>();

    public void add(Rule boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }
}
