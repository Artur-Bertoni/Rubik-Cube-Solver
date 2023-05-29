package domain.problems;

import domain.Cube;
import domain.Status;
import domain.searches.SearchNode;

import java.util.ArrayList;

/**
 * Define a problem to apply strategy A
 */
public class ProblemA extends VoraciousProblem {

    public ProblemA(Status start) {
        super(start);
    }

    public ProblemA(Cube start) {
        super(start);
    }

    /**
     * Returns the successor search nodes to the current search node,
     * based on the operations that can be performed with the cube at the current position
     */
    public ArrayList<SearchNode> successors(SearchNode e) {
        ArrayList<Status> successorStatuses = this.getSuccessorStatus(e.getCurrent());
        ArrayList<SearchNode> r = new ArrayList<>();
        int h;
        for (Status status : successorStatuses) {
            h = heuristics(status);
            r.add(new SearchNode(e, status, e.getDepth() + 1, e.getDepth() + h));
        }
        return r;
    }
}
