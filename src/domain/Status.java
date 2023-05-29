package domain;

import java.io.Serializable;

/**
 * The state is determined by the representation of a cube and the objective
 */
public class Status implements Serializable {

    private final String action;
    private Cube current;
    private Cube reach;


    public Status(Cube current, String action) {
        this.current = current;
        this.action = action;
        initializeReach();
    }

    public Status(Cube current, String action, Cube reach) {
        this.current = current;
        this.reach = reach;
        this.action = action;
    }


    public String getAction() {
        return action;
    }

    public Cube getCurrent() {
        return current;
    }

    public void setCurrent(Cube current) {
        this.current = current;
    }

    public Cube getReach() {
        return reach;
    }

    public void setReach(Cube reach) {
        this.reach = reach;
    }

    /**
     * Ideal goal cube solved
     */
    private void initializeReach() {
        Row f0 = new Row("red", "red", "red");
        Row f1 = new Row("red", "red", "red");
        Row f2 = new Row("red", "red", "red");
        Face c0 = new Face(f0, f1, f2);
        f0 = new Row("white", "white", "white");
        f1 = new Row("white", "white", "white");
        f2 = new Row("white", "white", "white");
        Face c1 = new Face(f0, f1, f2);
        f0 = new Row("orange", "orange", "orange");
        f1 = new Row("orange", "orange", "orange");
        f2 = new Row("orange", "orange", "orange");
        Face c2 = new Face(f0, f1, f2);
        f0 = new Row("yellow", "yellow", "yellow");
        f1 = new Row("yellow", "yellow", "yellow");
        f2 = new Row("yellow", "yellow", "yellow");
        Face c3 = new Face(f0, f1, f2);
        f0 = new Row("green", "green", "green");
        f1 = new Row("green", "green", "green");
        f2 = new Row("green", "green", "green");
        Face c4 = new Face(f0, f1, f2);
        f0 = new Row("blue", "blue", "blue");
        f1 = new Row("blue", "blue", "blue");
        f2 = new Row("blue", "blue", "blue");
        Face c5 = new Face(f0, f1, f2);
        reach = new Cube(c0, c1, c2, c3, c4, c5);
    }

    public String toString() {
        return this.current.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Status other = (Status) obj;
        return other.getCurrent().equals(current) && other.getReach().equals(reach);
    }

    @Override
    public int hashCode() {
        return this.current.hashCode() * this.action.hashCode();
    }
}
