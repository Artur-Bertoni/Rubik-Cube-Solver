package domain.searches;

import domain.Frontier;
import domain.problems.Problem;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * In this strategy, every time a better partial solution is found than the
 * previous one, the border is completely erased.
 * The consequences are that it moves fast but the solutions are long and the border may be empty.
 */
public class SearchForStrongPruning extends Search {

    public SearchForStrongPruning(Problem problem, boolean pruning) {
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

    /**
     * Shows the best solution found so far when it appears In this particularization of the method, each time a new solution appears, the border and the hash table will be restarted.
     */
    @Override
    protected void showPartialSolution(SearchNode current) {
        int advance = current.getCurrent().getCurrent().completed();
        if (advance > this.advance && advance < 54) {
            this.status = new Hashtable<>();
            this.frontier = new Frontier();
            createsTreeNodesList(this.problem.successors(current), current);
            this.advance = advance;
            System.out.println("Partial solution, improved cube: ");
            System.out.println("Completed: " + this.advance);
            System.out.println(current);
        }
    }
}
