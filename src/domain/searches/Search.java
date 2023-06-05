package domain.searches;

import domain.Frontier;
import domain.problems.Problem;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Operations to apply the search strategy in the general case
 */
public abstract class Search {

    protected int advance;
    protected Problem problem;
    protected int spatialComplexity;
    protected double temporalComplexity;
    protected Frontier frontier;
    protected boolean pruning;
    protected int depth;
    protected Hashtable<Integer, Integer> status;


    protected Search(Problem problem, boolean pruning) {
        this.problem = problem;
        frontier = new Frontier();
        status = new Hashtable<>();
        this.pruning = pruning;
        this.advance = 0;
    }


    public int getSpatialComplexity() {
        return spatialComplexity;
    }

    public double getTemporalComplexity() {
        return temporalComplexity;
    }


    /**
     * Pruning avoids repeating states that have already been visited
     */
    protected boolean pruning(SearchNode n) {
        boolean r = false;
        if (pruning) {
            Integer key = n.getCurrent().hashCode();
            Integer valor = status.get(key);
            if (valor == null || valor > n.getValue()) {
                status.put(key, n.getValue());
            } else {
                r = true;
            }
        }

        return !r;
    }

    /**
     * The search consists of extracting one of the nodes depending on the strategy
     * to check if it is a solution and if not,
     * generate the successors and add them to the search space
     */
    public String toFind() {  //General search
        ArrayList<SearchNode> LS;
        SearchNode current;
        SearchNode start = new SearchNode(null, problem.getStart(), 0, 0);
        frontier.insert(start);
        String solution;
        double startTime = System.currentTimeMillis();
        boolean resolved = false;

        do {
            current = frontier.eliminates();
            if (problem.objectiveTest(current.getCurrent())) {
                resolved = true;
            } else {
                LS = problem.successors(current);
                createsTreeNodesList(LS, current);
                spatialComplexity += LS.size();
            }
            showPartialSolution(current);
        } while (!resolved && !frontier.isEmpty());
        if (!this.frontier.isEmpty()) {
            solution = "Final Solution: \n\n" + current;
        } else {
            solution = "Solution not found";
        }
        temporalComplexity = (System.currentTimeMillis() - startTime) / 1000;
        return solution;
    }

    /**
     * Shows the best solution found so far when it appears
     */
    protected void showPartialSolution(SearchNode current) {
        int advance = current.getCurrent().getCurrent().completed();
        if (advance > this.advance && advance < 54) {
            this.advance = advance;
            System.out.println("Partial solution, improved cube: ");
            System.out.println("Completed: " + this.advance);
            System.out.println(current);
        }
    }

    /**
     * Defining the method you insert into the search space based on the strategy
     */
    protected abstract void createsTreeNodesList(
            ArrayList<SearchNode> LS, SearchNode current);

    @Override
    public String toString() {
        return "Time Complexity: " + getTemporalComplexity() + " s\n"
                + "Spatial complexity: " + getSpatialComplexity() + " nodes\n"
                + "Depth: " + depth;
    }
}
