package persistence;

import domain.Cube;
import domain.Face;
import domain.Row;

import java.io.*;

/**
 * File processing operations
 */
public class PersistenceOperations {

    /**
     * Reads a cube from a file with the address passed as a parameter the file must be written in the format of the cube
     */
    public static Cube readCube(String dir) {
        Cube cube = null;
        int boxes = 0;
        int rows = 0;
        int faces = 0;
        String[] boxesArray = new String[3];
        Row[] rowsArray = new Row[3];
        Face[] FacesArray = new Face[6];
        String[] lines;
        String color;
        String line;

        File file;
        FileReader fr = null;
        BufferedReader br;
        try {
            file = new File(dir);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                lines = line.split("\\s");
                for (String s : lines) {
                    color = s;
                    if (Cube.validColor(color)) {
                        boxesArray[boxes % 3] = color;  //every 3 is a row
                        boxes++;
                        if (boxes % 3 == 0) {
                            rowsArray[rows % 3] = new Row(boxesArray.clone());
                            rows++;
                            if (rows % 3 == 0) {
                                FacesArray[faces] = new Face(rowsArray.clone());
                                faces++;
                            }
                        }
                    }
                }
            }

            cube = new Cube(
                    FacesArray[0],
                    FacesArray[1],
                    FacesArray[2],
                    FacesArray[3],
                    FacesArray[4],
                    FacesArray[5]
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return cube;
    }

    /**
     * Write to a file
     */
    public static void savingInFile(String name, String content) {
        FileWriter file = null;
        PrintWriter pw = null;
        try {
            file = new FileWriter(name);
            pw = new PrintWriter(file);
            pw.print(content);
            System.out.println("a");
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            try {
                if (null != file) {
                    file.close();
                    pw.close();
                }
            } catch (Exception e2) {
                System.out.println();
                e2.printStackTrace();
            }
        }
    }
}
