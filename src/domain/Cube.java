package domain;

import java.io.FileWriter;
import java.io.PrintWriter;
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
    private Face face4;  //green  (top)
    private Face face0, face1, face2, face3;  //red, white, orange (back), yellow
    private Face face5;     //blue (down)


    public Cube(Face face0, Face face1, Face face2, Face face3, Face face4, Face face5) {
        this.face4 = face4;
        this.face0 = face0;
        this.face1 = face1;
        this.face2 = face2;
        this.face3 = face3;
        this.face5 = face5;
    }

    /**
     * Returns true if cube color is valid
     */
    public static boolean validColor(String color) {
        return Cube.is(color);
    }

    /**
     * Returns true if a string is within a list of strings
     */
    private static boolean is(String c) {
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
    public void X(int row) {
        ArrayList<Face> sisterFaces = sisterFaces();
        Face aux = sisterFaces.get(3);
        for (Face sisterFace : sisterFaces) {
            aux = sisterFace.turn(aux, row);
        }

        if (row == 0) {
            face4.transposeLeft();
        }

        if (row == 2) {
            face5.transposeRight();
        }
        place();
    }

    /**
     * With the cube well-placed, the Y movement consists of rotating one column downwards Y0,Y1,Y2
     */
    public void Y(int row) {
        rotateReverse();
        X(2 - row);
    }

    /**
     * With the cube well-placed, the Z movement consists of turning one face to the right
     * That is, Z0 transposes the red face, Z1 applies on the plane behind the red face (intermediate) and Z2 transposes the orange face
     */
    public void Z(int row) {
        rotateDown();
        X(2 - row);
    }

    /**
     * To perform one of the possible operations
     */
    public void operateCube(ArrayList<String> op) {
        for (String s : op) {
            final int row = Integer.parseInt(String.valueOf(s.charAt(1)));

            switch (s.charAt(0)) {
                case 'X' -> X(row);
                case 'Y' -> Y(row);
                case 'Z' -> Z(row);
            }
        }
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateLeft() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0 = faces.get(3);
        face1 = faces.get(0);
        face2 = faces.get(1);
        face3 = faces.get(2);
        face4.transposeLeft();
        face5.transposeRight();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateRight() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0 = faces.get(1);
        face1 = faces.get(2);
        face2 = faces.get(3);
        face3 = faces.get(0);
        face4.transposeRight();
        face5.transposeLeft();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateDown() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0 = faces.get(4);
        face1.transposeLeft();
        face2 = faces.get(5);
        face2.transposeRight();
        face2.transposeRight();
        face3.transposeRight();

        face4 = faces.get(2);
        face4.transposeRight();
        face4.transposeRight();
        face5 = faces.get(0);
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateUp() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0 = faces.get(5);
        face1.transposeRight();
        face2 = faces.get(4);
        face2.transposeRight();
        face2.transposeRight();

        face3.transposeLeft();

        face4 = faces.get(0);

        face5 = faces.get(2);
        face5.transposeRight();
        face5.transposeRight();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateDirect() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0.transposeRight();
        face1 = faces.get(4);
        face1.transposeRight();

        face2.transposeLeft();

        face3 = faces.get(5);
        face3.transposeRight();

        face4 = faces.get(3);
        face4.transposeRight();

        face5 = faces.get(1);
        face5.transposeRight();
    }

    /**
     * Manipulates the cube in a different position from the original (red face in front, green above) is necessary for some operations
     */
    public void rotateReverse() {
        ArrayList<Face> faces = (ArrayList<Face>) getFaces().clone();
        face0.transposeLeft();
        face1 = faces.get(5);
        face1.transposeLeft();

        face2.transposeRight();

        face3 = faces.get(4);
        face3.transposeLeft();

        face4 = faces.get(1);
        face4.transposeLeft();

        face5 = faces.get(3);
        face5.transposeLeft();
    }

    /**
     * Returns faces that are positioned around the cube when the cube is in the specified position
     */
    public ArrayList<Face> sisterFaces() {
        ArrayList<Face> r = new ArrayList<>();
        r.add(face0);
        r.add(face1);
        r.add(face2);
        r.add(face3);
        return r;
    }

    /**
     * Returns faces in order according to the indicated position
     */
    public ArrayList<Face> getFaces() {
        ArrayList<Face> faces = new ArrayList<>();
        faces.add(face0);
        faces.add(face1);
        faces.add(face2);
        faces.add(face3);
        faces.add(face4);
        faces.add(face5);

        return faces;
    }

    @Override
    public String toString() {
        String r = "Face 0:\n";
        r += face0 + "\n";
        r += "Face 1:\n";
        r += face1 + "\n";
        r += "Face 2:\n";
        r += face2 + "\n";
        r += "Face 3:\n";
        r += face3 + "\n";
        r += "Face 4:\n";
        r += face4 + "\n";
        r += "Face 5:\n";
        r += face5 + "\n";

        return r;
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
        boxes.add(face0.getFaceColor());
        boxes.add(face1.getFaceColor());
        boxes.add(face2.getFaceColor());
        boxes.add(face3.getFaceColor());
        boxes.add(face4.getFaceColor());
        boxes.add(face5.getFaceColor());
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
        return new Cube((Face) face0.clone(), (Face) face1.clone(), (Face) face2.clone(), (Face) face3.clone(),
                (Face) face4.clone(), (Face) face5.clone());
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

    /**
     * Generates a file with the cube so that it can be loaded later if a default value is needed
     */
    public void persistCube() {
        FileWriter file = null;
        PrintWriter pw;
        try {
            file = new FileWriter("cube.txt");
            pw = new PrintWriter(file);
            pw.println(this.getContent());

        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            try {
                if (null != file) {
                    file.close();
                }
            } catch (Exception e2) {
                System.out.println();
                e2.printStackTrace();
            }
        }
    }

    /**
     * Returns a string with the formatted cube information to save to a file
     */
    private String getContent() {
        StringBuilder res = new StringBuilder();
        ArrayList<Face> faces = this.getFaces();
        ArrayList<Row> rows;
        for (Face face : faces) {
            rows = face.getRows();
            for (Row f : rows) {
                ArrayList<String> boxes = f.getBoxes();
                for (String c : boxes) {
                    res.append(c).append(" ");
                }
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
