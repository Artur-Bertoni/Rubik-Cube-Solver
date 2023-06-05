package domain.searches;

import domain.problems.Problem;

import java.util.Vector;

/**
 * The depth limit is increased at each iteration
 */
public class IterativeDepthSearch extends BoundedDepthSearch {

    protected int iteration;
    protected double totalTimeComplexity;
    protected int finalSpatialComplexity;

    protected Vector<Double> temporalComplexityIterations;
    protected Vector<Integer> spatialComplexityIterations;


    public IterativeDepthSearch(Problem problem,
                                int maximumDepth, int iteration, boolean pruning) {
        super(problem, maximumDepth, pruning);
        this.iteration = iteration;
        temporalComplexityIterations = new Vector<>();
        spatialComplexityIterations = new Vector<>();
    }

    /**
     * When nodes are completed in an iteration and the solution is not found, the limit is increased.
     */
    @Override
    public String toFind() {
        String solution;
        double timeStartTotal = System.currentTimeMillis();
        do {
            solution = super.toFind();
            if (solution == null) {
                System.out.println("Tree in depth " + maximumDepth + " completed");
                maximumDepth += iteration;
                System.out.println("Exploring new depth :" + maximumDepth);
                temporalComplexityIterations.add(super.getTemporalComplexity());
                spatialComplexityIterations.add(super.getSpatialComplexity());
            }
        } while (solution == null);
        totalTimeComplexity = (System.currentTimeMillis() - timeStartTotal) / 1000;
        finalSpatialComplexity = super.getSpatialComplexity();
        return solution;
    }


    @Override
    public double getTemporalComplexity() {
        return totalTimeComplexity;
    }

    @Override
    public int getSpatialComplexity() {
        return finalSpatialComplexity;
    }


    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("Spatial complexity in each iteration:\n");
        for (int i = 0; i < spatialComplexityIterations.size(); i++) {
            r.append("\tIteration ").append(i).append(": ").append(spatialComplexityIterations.get(i)).append("\n");
        }
        r.append("Time complexity in each iteration:\n");
        for (int i = 0; i < temporalComplexityIterations.size(); i++) {
            r.append("\tIteration ").append(i).append(": ").append(temporalComplexityIterations.get(i)).append("\n");
        }
        r.append("Final Maximum Depth: ").append(maximumDepth);
        r.append("\nTotal Complexity:\n");
        r.append(super.toString());
        return r.toString();
    }
}
