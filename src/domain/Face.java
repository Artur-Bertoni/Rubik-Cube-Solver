package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a face of the cube, a set of rows in a certain order
 */
public class Face implements Cloneable, Serializable {

    private Row row0;
    private Row row1;
    private Row row2;

    public Face(Row row0, Row row1, Row row2) {
        this.row0 = row0;
        this.row1 = row1;
        this.row2 = row2;
    }

    public Face(Row[] rows) {
        if (rows.length == 3) {
            this.row0 = rows[0];
            this.row1 = rows[1];
            this.row2 = rows[2];
        }
    }

    public Face() {
    }

    public Row getRow0() {
        return row0;
    }

    public Row getRow1() {
        return row1;
    }

    public Row getRow2() {
        return row2;
    }

    /**
     * Returns the value of the face based on its checkboxes
     */
    public int getFaceValue() {
        String color = this.getFaceColor();
        ArrayList<Row> rows = this.getRows();
        ArrayList<String> boxes;
        int value = 0;
        switch (color) {
            case "red" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "white", "yellow", "green", "blue" -> value += 1;
                            case "orange" -> value += 2;
                        }
                    }
                }
            }
            case "white" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "red", "orange", "green", "blue" -> value += 1;
                            case "yellow" -> value += 2;
                        }
                    }
                }
            }
            case "orange" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "yellow", "orange", "green", "blue" -> value += 1;
                            case "red" -> value += 2;
                        }
                    }
                }
            }
            case "yellow" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "red", "orange", "green", "blue" -> value += 1;
                            case "white" -> value += 2;
                        }
                    }
                }
            }
            case "green" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "red", "orange", "green", "white" -> value += 1;
                            case "blue" -> value += 2;
                        }
                    }
                }
            }
            case "blue" -> {
                for (Row f : rows) {
                    boxes = f.getBoxes();
                    for (String c : boxes) {
                        switch (c) {
                            case "red", "orange", "yellow", "white" -> value += 1;
                            case "green" -> value += 2;
                        }
                    }
                }
            }
        }
        return value;
    }

    public ArrayList<Row> getRows() {
        ArrayList<Row> r = new ArrayList<>();
        r.add(row0);
        r.add(row1);
        r.add(row2);
        return r;
    }

    /**
     * Modifies face information to simulate a row rotation, corresponds to X operations
     */
    public Face turn(Face c, int fila) {
        Face current = (Face) clone();
        Row f = c.getRows().get(fila);
        getRows().get(fila).turn(f);
        return current;
    }

    /**
     * Transposing a face corresponds to the operations Z Counterclockwise direction
     */
    public void transposeLeft() {
        Row rowA = new Row(row0.getBoxes().get(2), row1.getBoxes().get(2),
                row2.getBoxes().get(2));
        Row rowB = new Row(row0.getBoxes().get(1),
                row1.getBoxes().get(1),
                row2.getBoxes().get(1));
        Row rowC = new Row(row0.getBoxes().get(0),
                row1.getBoxes().get(0),
                row2.getBoxes().get(0));
        row0.turn(rowA);
        row1.turn(rowB);
        row2.turn(rowC);
    }

    /**
     * Transposing a face corresponds to the operations Z Direction that follow the hands of a clock
     */
    public void transposeRight() {
        Row rowA = new Row(row2.getBoxes().get(0),
                row1.getBoxes().get(0),
                row0.getBoxes().get(0));
        Row rowB = new Row(row2.getBoxes().get(1),
                row1.getBoxes().get(1),
                row0.getBoxes().get(1));
        Row rowC = new Row(row2.getBoxes().get(2),
                row1.getBoxes().get(2),
                row0.getBoxes().get(2));
        row0.turn(rowA);
        row1.turn(rowB);
        row2.turn(rowC);
    }

    /**
     * The color of a face is the color of its central square
     */
    public String getFaceColor() {
        return row1.getBoxes().get(1);
    }


    @Override
    public String toString() {
        String r = "";
        r += "------------------------------------\n";
        r += row0 + "\n";
        r += row1 + "\n";
        r += row2 + "\n";
        r += "------------------------------------";
        return r;
    }

    public String toStringForFile() {
        String r = row0.toStringForFile() + "\n";
        r += row1.toStringForFile() + "\n";
        r += row2.toStringForFile();
        return r;
    }

    @Override
    public Object clone() {
        return new Face((Row) row0.clone(), (Row) row1.clone(), (Row) row2.clone());
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = true;
        ArrayList<Row> rows = getRows();
        ArrayList<Row> objRows = ((Face) obj).getRows();
        if (rows.size() == objRows.size()) {
            for (int i = 0; i < rows.size(); i++) {
                if (!rows.get(i).equals(objRows.get(i))) {
                    equals = false;
                    break;
                }
            }
        } else {
            equals = false;
        }
        return equals;
    }

    /**
     * Number of cells that correspond to the color of this face
     */
    public int completed() {
        String color = getFaceColor();
        int r = 0;

        for (Row f : getRows()) {
            for (String s : f.getBoxes()) {
                if (s.equals(color)) {
                    r++;
                }
            }
        }
        return r;
    }
}
