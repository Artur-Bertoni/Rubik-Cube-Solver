package domain.searches;

import domain.problems.Problem;

import java.util.ArrayList;

/**
 * Voracious search takes nodes ordered but without heuristics
 */
public class VoraciousSearch extends Search {

    public VoraciousSearch(Problem problem, boolean pruning) {
        super(problem, pruning);
    }

    /**
     * Nodes are inserted sorted according to their value
     */
    @Override
    protected void createsTreeNodesList(ArrayList<SearchNode> LS,
                                        SearchNode current) {
        for (SearchNode n : LS) {
            if (pruning(n)) {
                frontier.insertOrdered(n);
            }
        }
    }
}
