package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Cloneable, Serializable {

    private int box0;
    private int box1;
    private int box2;


    public Row(String box0, String box1, String box2) {
        this.box0 = translateAInt(box0);
        this.box1 = translateAInt(box1);
        this.box2 = translateAInt(box2);

    }

    public Row(String[] array) {
        if (array.length == 3) {
            this.box0 = translateAInt(array[0]);
            this.box1 = translateAInt(array[1]);
            this.box2 = translateAInt(array[2]);
        }
    }

    private int translateAInt(String color) {
        return switch (color) {
            case "red" -> 0;
            case "white" -> 1;
            case "orange" -> 2;
            case "yellow" -> 3;
            case "green" -> 4;
            case "blue" -> 5;
            default -> 0;
        };
    }

    private String translateAString(int color) {
        return switch (color) {
            case 0 -> "red";
            case 1 -> "white";
            case 2 -> "orange";
            case 3 -> "yellow";
            case 4 -> "green";
            case 5 -> "blue";
            default -> "";
        };
    }

    /**
     * Cells in a row from left to right
     */
    public ArrayList<String> getBoxes() {
        ArrayList<String> r = new ArrayList<>();
        r.add(translateAString(box0));
        r.add(translateAString(box1));
        r.add(translateAString(box2));
        return r;
    }

    /**
     * Rotate a row
     */
    public void turn(Row l) {
        box0 = translateAInt(l.getBoxes().get(0));
        box1 = translateAInt(l.getBoxes().get(1));
        box2 = translateAInt(l.getBoxes().get(2));
    }


    @Override
    public Object clone() {
        return new Row(translateAString(box0), translateAString(box1), translateAString(box2));
    }

    public String toString() {
        String r = translateAString(box0);
        String aux;
        r = fill(r);
        aux = fill(translateAString(box1));
        r += " || " + aux + " || ";
        aux = fill(translateAString(box2));
        r += aux;
        return r;
    }

    /**
     * Used by toString to give format
     */
    private String fill(String name) {
        StringBuilder nameBuilder = new StringBuilder(name);
        nameBuilder.append(" ".repeat(Math.max(0, 8 - nameBuilder.length() + 1)));
        name = nameBuilder.toString();
        return name;
    }

    /**
     * All cells of the same color, possible heuristics
     */
    public boolean correct() {
        return box0 == box1
                && box1 == box2;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = true;
        ArrayList<String> boxes = getBoxes();
        ArrayList<String> objBoxes = ((Row) obj).getBoxes();

        if (boxes.size() == objBoxes.size()) {
            for (int i = 0; i < boxes.size(); i++) {
                if (!boxes.get(i).equals(objBoxes.get(i))) {
                    equals = false;
                }
            }
        } else {
            equals = false;
        }
        return equals;
    }
}
