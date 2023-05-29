package domain;

import domain.searches.SearchNode;

import java.util.Vector;

/**
 * The border contains the search nodes that must be processed to reach the solution
 */
public class Frontier {

    private Vector<SearchNode> frontier;

    /**
     *
     */
    public Frontier() {
        frontier = new Vector<>();
    }

    /**
     * Used to obtain a random position of the boundary
     */
    private static double uniform(int b) {
        return 0 + (b) * Math.random();
    }

    /**
     * Remove a search node from the border
     */
    public SearchNode eliminates() {
        SearchNode r = frontier.firstElement();
        frontier.remove(r);
        return r;
    }

    /**
     * Insert a back-up node at the border
     */
    public void insert(SearchNode sn) {
        frontier.add(sn);
    }

    /**
     * Inserts a search node at any position of the border
     */
    public void insertRandom(SearchNode es) {
        frontier.add((int) uniform(frontier.size() - 1), es);
    }

    /**
     * Inserts a node sorted based on a value
     */
    public void insertOrdered(SearchNode sn) {
        boolean inside = false;
        int min = 0;
        int max = this.frontier.size() - 1;
        int middle;
        while (!inside) {
            middle = (max + min) / 2;
            if (max <= 0) {
                this.frontier.add(sn);
                inside = true;
            } else {
                if (middle == max || middle == min) {
                    this.frontier.add(middle, sn);
                    inside = true;
                } else {
                    if (sn.compareTo(frontier.get(middle)) < 0) {
                        max = middle;
                    } else {
                        min = middle;
                    }
                }
            }
        }
    }


    /**
     * Inserts a lookup node ahead of the border
     */
    public void insertFirst(SearchNode first) {
        frontier.add(0, first);
    }

    /**
     * True when the border contains no nodes
     */
    public boolean isEmpty() {
        return frontier.isEmpty();
    }


    public Vector<SearchNode> getFrontier() {
        return frontier;
    }

    public void setFrontier(Vector<SearchNode> frontier) {
        this.frontier = frontier;
    }
}
