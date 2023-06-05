package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the cube in the program
 */
public class Cube implements Cloneable, Serializable {

    private static final String[] colors = {"red", "white", "orange", "yellow", "green", "blue"};

    /**
     * Faces in order of how the physical cube should look to apply the operations correctly
     * The cube must be placed with the face0 (red) in front, and the green face above (face5)
     */
    private Face topFace;  //green  (top)
    private Face frontFace, rightFace, backFace, leftFace;  //red, white, orange (back), yellow
    private Face downFace;     //blue (down)


    public Cube(Face frontFace, Face rightFace, Face backFace, Face leftFace, Face topFace, Face downFace) {
        this.topFace = topFace;
        this.frontFace = frontFace;
        this.rightFace = rightFace;
        this.backFace = backFace;
        this.leftFace = leftFace;
        this.downFace = downFace;
    }

    /**
     * Returns true if cube color is valid
     */
    public static boolean validColor(String color) {
        return Cube.isValid(color);
    }

    /**
     * Returns true if a string is within a list of strings
     */
    private static boolean isValid(String c) {
        boolean is = false;
        for (int i = 0; i < Cube.colors.length; i++) {
            if (c.equals(Cube.colors[i])) {
                is = true;
                break;
            }
        }
        return is;
    }

    /**
     * Placing the cube in the indicated position (red, front; green, top),
     * a movement X corresponds to rotating X0 X1 X2 one row to the right
     */
    public void moveARowToTheRight(int row) {
        ArrayList<Face> sisterFaces = sisterFaces();
        Face aux = sisterFaces.get(3);
        for (Face sisterFace : sisterFaces) {
            aux = sisterFace.turn(aux, row);
        }

        if (row == 0) {
            topFace.transposeLeft();
        }

        if (row == 2) {
            downFace.transposeRight();
        }
        place();
    }

    /**
     * With the cube well-placed, the Y movement consists of rotating one column downwards Y0,Y1,Y2
     */
    public void moveOneColumnDown(int row) {
        rotateReverse();
        moveARowToTheRight(2 - row);
    }

    /**
     * With the cube well-placed, the Z movement consists of turning one face to the right
     * That is, Z0 transposes the red face, Z1 applies on the plane behind the red face (intermediate) and Z2 transposes the orange face
     */
    public void rotate(int row) {
        rotateDown();
        moveARowToTheRight(2 - row);
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateLeft() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        frontFace = faces.get(3);
        rightFace = faces.get(0);
        backFace = faces.get(1);
        leftFace = faces.get(2);
        topFace.transposeLeft();
        downFace.transposeRight();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateRight() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        frontFace = faces.get(1);
        rightFace = faces.get(2);
        backFace = faces.get(3);
        leftFace = faces.get(0);
        topFace.transposeRight();
        downFace.transposeLeft();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateDown() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        frontFace = faces.get(4);
        rightFace.transposeLeft();
        backFace = faces.get(5);
        backFace.transposeRight();
        backFace.transposeRight();
        leftFace.transposeRight();

        topFace = faces.get(2);
        topFace.transposeRight();
        topFace.transposeRight();
        downFace = faces.get(0);
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateDirect() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        frontFace.transposeRight();
        rightFace = faces.get(4);
        rightFace.transposeRight();

        backFace.transposeLeft();

        leftFace = faces.get(5);
        leftFace.transposeRight();

        topFace = faces.get(3);
        topFace.transposeRight();

        downFace = faces.get(1);
        downFace.transposeRight();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateReverse() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        frontFace.transposeLeft();
        rightFace = faces.get(5);
        rightFace.transposeLeft();

        backFace.transposeRight();

        leftFace = faces.get(4);
        leftFace.transposeLeft();

        topFace = faces.get(1);
        topFace.transposeLeft();

        downFace = faces.get(3);
        downFace.transposeLeft();
    }

    /**
     * Returns faces that are positioned around the cube when the cube is in the specified position
     */
    public ArrayList<Face> sisterFaces() {
        ArrayList<Face> r = new ArrayList<>();
        r.add(frontFace);
        r.add(rightFace);
        r.add(backFace);
        r.add(leftFace);
        return r;
    }

    /**
     * Returns faces in order according to the indicated position
     */
    public ArrayList<Face> getFaces() {
        ArrayList<Face> faces = new ArrayList<>();
        faces.add(frontFace);
        faces.add(rightFace);
        faces.add(backFace);
        faces.add(leftFace);
        faces.add(topFace);
        faces.add(downFace);

        return faces;
    }

    @Override
    public String toString() {
        Face emptyFace = new Face(
                new Row(" ", " ", " "),
                new Row(" ", " ", " "),
                new Row(" ", " ", " ")
        );

        return "                                      -----------------------------------\n" +
                "   " + emptyFace.getRow0() + " " + topFace.getRow0() + "\n" +
                "   " + emptyFace.getRow1() + " " + topFace.getRow1() + "\n" +
                "   " + emptyFace.getRow2() + " " + topFace.getRow2() + "\n" +
                "  ----------------------------------  ----------------------------------  ----------------------------------  ----------------------------------\n" +
                "|| " + leftFace.getRow0() + " " + frontFace.getRow0() + " " + rightFace.getRow0() + " " + backFace.getRow0() + "\n" +
                "|| " + leftFace.getRow1() + " " + frontFace.getRow1() + " " + rightFace.getRow1() + " " + backFace.getRow1() + "\n" +
                "|| " + leftFace.getRow2() + " " + frontFace.getRow2() + " " + rightFace.getRow2() + " " + backFace.getRow2() + "\n" +
                "  ----------------------------------  ----------------------------------  ----------------------------------  ----------------------------------\n" +
                "   " + emptyFace.getRow0() + " " + downFace.getRow0() + "\n" +
                "   " + emptyFace.getRow1() + " " + downFace.getRow1() + "\n" +
                "   " + emptyFace.getRow2() + " " + downFace.getRow2() + "\n" +
                "                                      -----------------------------------\n";
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = true;
        Cube other = (Cube) obj;
        other.place();
        place();

        ArrayList<Face> faces = getFaces();
        ArrayList<Face> objFaces = other.getFaces();
        if (faces.size() == objFaces.size()) {
            for (int i = 0; i < faces.size(); i++) {
                if (!faces.get(i).equals(objFaces.get(i))) {
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
     * Devuelve el color de las caras en orden
     */
    public ArrayList<String> getFacesColors() {
        ArrayList<String> boxes = new ArrayList<>();
        boxes.add(frontFace.getFaceColor());
        boxes.add(rightFace.getFaceColor());
        boxes.add(backFace.getFaceColor());
        boxes.add(leftFace.getFaceColor());
        boxes.add(topFace.getFaceColor());
        boxes.add(downFace.getFaceColor());
        return boxes;
    }

    /**
     * Place the cube in the indicated position as it should be physically positioned for resolution
     */
    public void place() {
        ArrayList<String> colors = getFacesColors();
        if (notPlaced()) {
            switch (colors.indexOf("red")) {
                case 0, 1, 2, 3 -> {
                    while (!getFacesColors().get(0).equals("red")) {
                        rotateRight();
                    }
                }
                default -> {
                    while (!getFacesColors().get(0).equals("red")) {
                        rotateDown();
                    }
                }
            }
            while (notPlaced()) {
                rotateDirect();
            }
        }
    }

    /**
     * True if the cube is placed in the indicated position
     */
    public boolean notPlaced() {
        ArrayList<String> cubePlaced = new ArrayList<>();
        cubePlaced.add("red");
        cubePlaced.add("white");
        cubePlaced.add("orange");
        cubePlaced.add("yellow");
        cubePlaced.add("green");
        cubePlaced.add("blue");
        boolean placed = cubePlaced.equals(getFacesColors());
        return !placed;
    }

    @Override
    public Object clone() {
        return new Cube((Face) frontFace.clone(), (Face) rightFace.clone(), (Face) backFace.clone(), (Face) leftFace.clone(),
                (Face) topFace.clone(), (Face) downFace.clone());
    }

    /**
     * Returns the number of check boxes that are in place
     */
    public int completed() {
        int r = 0;
        for (Face c : this.getFaces()) {
            r += c.completed();
        }
        return r;
    }

    /**
     * Returns the sum of the distances of the cells to their correct position
     * If a square is on its face it is worth 0 if it is on an adjacent face 1...
     */
    public int getValueDistanceManhattan() {
        ArrayList<Face> faces = this.getFaces();
        int valor = 0;
        for (Face c : faces) {
            valor += c.getFaceValue();
        }
        return valor;
    }

    /**
     * Identify a cube
     */
    public int hashCode() {
        int hashCode = 7;
        int[] primes = {2, 3, 5, 7, 11, 13,
                17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 101, 59,
                61, 67, 71, 73, 79, 83,
                103, 89, 97, 107, 109, 113,
                127, 131, 137, 139, 149, 151,
                157, 163, 167, 173, 179, 181,
                191, 193, 197, 199, 211, 223,
                227, 229, 233, 239, 241, 251};
        ArrayList<Face> faces = getFaces();
        ArrayList<Row> rows;
        ArrayList<String> boxes;

        int i = 0;
        for (Face face : faces) {
            rows = face.getRows();
            for (Row f : rows) {
                boxes = f.getBoxes();
                for (String c : boxes) {
                    hashCode += primes[i++] * get(c) * (i);
                }
            }
        }
        return hashCode;
    }

    /**
     * Assign an integer to each color
     */
    public int get(String color) {
        return switch (color) {
            case "red" -> 257;
            case "white" -> 263;
            case "orange" -> 269;
            case "yellow" -> 271;
            case "green" -> 277;
            case "blue" -> 281;
            default -> 1;
        };
    }
}
