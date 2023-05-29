package domain.problems;

import domain.Cube;
import domain.Status;
import domain.searches.SearchNode;

import java.util.ArrayList;

/**
 * The search strategy is applied first the best recursive that values the nodes with the
 * highest value between their heuristics plus their cost and the value of the parent node
 */
public class ProblemBRPM extends VoraciousProblem {

    public ProblemBRPM(Status start) {
        super(start);
    }

    public ProblemBRPM(Cube start) {
        super(start);
    }

    /**
     * Returns the successor search nodes to the current search node,
     * based on the operations that can be performed with the cube at the current position
     */
    public ArrayList<SearchNode> successors(SearchNode e) {
        ArrayList<Status> successorStatuses = this.getSuccessorStatus(e.getCurrent());
        ArrayList<SearchNode> r = new ArrayList<>();
        int fested, fprevious, h;
        for (Status status : successorStatuses) {
            fested = heuristics(status) + e.getDepth() + 1;
            fprevious = heuristics(e.getEstado()) + e.getDepth();
            h = Math.max(fested, fprevious);
            r.add(new SearchNode(e, status, e.getDepth() + 1, h));
        }
        return r;
    }
}
