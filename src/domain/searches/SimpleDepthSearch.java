package domain.searches;

import domain.problems.Problem;

import java.util.ArrayList;

/**
 * General case depth
 */
public class SimpleDepthSearch extends Search {

    public SimpleDepthSearch(Problem problem, boolean pruning) {
        super(problem, pruning);
    }

    /**
     * To do the deep search the nodes are inserted in front
     */
    @Override
    protected void createsTreeNodesList(ArrayList<SearchNode> LS,
                                        SearchNode current) {
        //Simple depth last to enter first to exit
        for (SearchNode n : LS) {
            if (pruning(n)) {
                frontier.insertFirst(n);
            }
        }
    }
}
