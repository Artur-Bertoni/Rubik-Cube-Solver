package domain.problems;

import domain.Cube;
import domain.Status;
import domain.searches.SearchNode;

import java.util.ArrayList;

/**
 * To apply voracious search
 */
public class VoraciousProblem extends Problem {

    public VoraciousProblem(Status start) {
        super(start);
    }

    public VoraciousProblem(Cube start) {
        super(start);
    }

    /**
     * Returns the successors of the current search node
     */
    @Override
    public ArrayList<SearchNode> successors(SearchNode e) {
        ArrayList<Status> successorStatuses = this.getSuccessorStatus(e.getCurrent());
        ArrayList<SearchNode> r = new ArrayList<>();
        int h;
        for (Status status : successorStatuses) {
            h = heuristics(status);
            r.add(new SearchNode(e, status, e.getDepth() + 1, h));
        }
        return r;
    }

    /**
     * The implemented heuristics will take into account the faces obtained in the cube
     */
    protected int heuristics(Status e) {
        return e.getCurrent().getValueDistanceManhattan() - e.getCurrent().completed();
    }
}
