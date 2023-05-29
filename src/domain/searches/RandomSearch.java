package domain.searches;

import domain.problems.Problem;

import java.util.ArrayList;

public class RandomSearch extends Search {

    public RandomSearch(Problem problem, boolean pruning) {
        super(problem, pruning);
    }

    /**
     * The movements are random so the nodes are inserted anywhere on the border
     */
    @Override
    protected void createsTreeNodesList(ArrayList<SearchNode> LS, SearchNode current) {
        for (SearchNode n : LS) {
            if (pruning(n)) {
                frontier.insertRandom(n);
            }
        }
    }
}
