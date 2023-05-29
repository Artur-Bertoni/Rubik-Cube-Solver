package domain.searches;

import domain.problems.Problem;

import java.util.ArrayList;

public class SearchWidth extends Search {

    public SearchWidth(Problem problem, boolean pruning) {
        super(problem, pruning);
    }


    /**
     * To search in width the nodes are inserted from behind
     */
    @Override
    protected void createsTreeNodesList(ArrayList<SearchNode> LS,
                                        SearchNode current) {
        //first in width, tail border
        for (SearchNode n : LS) {
            if (pruning(n))
                frontier.insert(n);
        }
    }
}
