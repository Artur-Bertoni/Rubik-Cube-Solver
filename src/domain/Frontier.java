package domain;

import domain.searches.SearchNode;

import java.util.Vector;

/**
 * The border contains the search nodes that must be processed to reach the solution
 */
public class Frontier {

    private final Vector<SearchNode> frontier;

    public Frontier() {
        frontier = new Vector<>();
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
}
