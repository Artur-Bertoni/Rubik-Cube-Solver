package domain.searches;

import domain.problems.Problem;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * It will be searched in depth with a maximum dimension
 */
public class BoundedDepthSearch extends SimpleDepthSearch {

    protected int maximumDepth;

    public BoundedDepthSearch(Problem problem, int maximumDepth, boolean pruning) {
        super(problem, pruning);
        this.maximumDepth = maximumDepth;
    }

    /**
     * Strategy for deep search
     */
    @Override
    public String toFind() {
        status = new Hashtable<>();
        ArrayList<SearchNode> LS;
        SearchNode current;
        SearchNode start = new SearchNode(null, problem.getStart(), 0, 0);
        frontier.insert(start);
        double startTime = System.currentTimeMillis();
        boolean result = false;
        String solution = null;

        do {
            current = frontier.eliminates();
            if (problem.objectiveTest(current.getCurrent())) {
                result = true;
                if (!this.frontier.isEmpty()) {
                    solution = "Final Solution: \n" + current;
                } else {
                    solution = "Solution not found";
                }
            } else {
                LS = problem.successors(current);
                if (LS.get(0).getDepth() <= maximumDepth) { //Bounded depth search
                    createsTreeNodesList(LS, current);
                    spatialComplexity += LS.size();
                }
            }
            showPartialSolution(current);
        } while (!result && !frontier.isEmpty());
        temporalComplexity = (System.currentTimeMillis() - startTime) / 1000;
        return solution;
    }
}
