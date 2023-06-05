package domain;

import java.io.Serializable;

/**
 * The state is determined by the representation of a cube and the objective
 */
public class Status implements Serializable {

    private final String action;
    private final Cube current;
    private Cube reach;


    public Status(Cube current, String action) {
        this.current = current;
        this.action = action;
        initializeReach();
    }


    public String getAction() {
        return action;
    }

    public Cube getCurrent() {
        return current;
    }

    public Cube getReach() {
        return reach;
    }


    /**
     * Ideal goal cube solved
     */
    private void initializeReach() {
        Row r0 = new Row("red", "red", "red");
        Row r1 = new Row("red", "red", "red");
        Row r2 = new Row("red", "red", "red");
        Face frontFace = new Face(r0, r1, r2);
        r0 = new Row("white", "white", "white");
        r1 = new Row("white", "white", "white");
        r2 = new Row("white", "white", "white");
        Face rightFace = new Face(r0, r1, r2);
        r0 = new Row("orange", "orange", "orange");
        r1 = new Row("orange", "orange", "orange");
        r2 = new Row("orange", "orange", "orange");
        Face backFace = new Face(r0, r1, r2);
        r0 = new Row("yellow", "yellow", "yellow");
        r1 = new Row("yellow", "yellow", "yellow");
        r2 = new Row("yellow", "yellow", "yellow");
        Face leftFace = new Face(r0, r1, r2);
        r0 = new Row("green", "green", "green");
        r1 = new Row("green", "green", "green");
        r2 = new Row("green", "green", "green");
        Face topFace = new Face(r0, r1, r2);
        r0 = new Row("blue", "blue", "blue");
        r1 = new Row("blue", "blue", "blue");
        r2 = new Row("blue", "blue", "blue");
        Face downFace = new Face(r0, r1, r2);

        reach = new Cube(frontFace, rightFace, backFace, leftFace, topFace, downFace);
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
