package domain.searches;

import domain.Status;

import java.io.Serializable;

/**
 * To get create the search tree based on states
 */
public class SearchNode implements Comparable, Serializable {

    private final SearchNode parent;
    private final Status current;
    private final int depth;
    private final int value;
    private final String operations;


    public SearchNode(SearchNode parent, Status current, int depth, int value) {
        this.parent = parent;
        this.current = current;
        this.depth = depth;
        this.value = value;
        this.operations = getParentOperations() + current.getAction();
    }

    private String getParentOperations() {
        String result = "";
        if (parent != null) {
            result = parent.getOperations() + " -> ";
        }
        return result;
    }


    public String getOperations() {
        return operations;
    }

    public int getDepth() {
        return depth;
    }

    public int getValue() {
        return value;
    }

    public Status getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Steps: " + this.getDepth() + "\n" + this.operations + "\n" + this.current;
    }

    @Override
    public int hashCode() {
        return current.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SearchNode other = (SearchNode) obj;

        return other.getCurrent().equals(current);
    }

    @Override
    public int compareTo(Object o) {
        return value - ((SearchNode) o).getValue();
    }
}
