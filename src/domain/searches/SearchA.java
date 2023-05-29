package domain.searches;

import domain.problems.Problem;

/**
 * The search will be carried out with strategy A defined in ProblemSearchA
 */
public class SearchA extends VoraciousSearch {

    public SearchA(Problem problem, boolean pruning) {
        super(problem, pruning);
    }
}
