package presentation;

import domain.Cube;
import domain.Face;
import domain.Row;
import domain.problems.ProblemA;
import domain.searches.IterativeDepthSearch;
import persistence.PersistenceOperations;

/**
 * A user interface
 */
public class UserInterface {

    public void menu(String dir) {

        Cube cube = PersistenceOperations.readCube(dir);

        // A problem type is selected

//        Problem p = new Problem(cube);
        ProblemA p = new ProblemA(cube);
//        ProblemBRPM p = new ProblemBRPM(cube);
//        VoraciousProblem p = new VoraciousProblem(cube);


        // A search strategy is selected

//        VoraciousSearch b = new VoraciousSearch(p, true);
//        SearchForStrongPruning b = new SearchForStrongPruning(p, true);
//        SearchA b = new SearchA(p, true);
//        RandomSearch b = new RandomSearch(p, true);
//        SearchWidth b = new SearchWidth(p, false);
//        SimpleDepthSearch b = new SimpleDepthSearch(p, true);
//        BoundedDepthSearch b = new BoundedDepthSearch(p, 6, true);
        IterativeDepthSearch b = new IterativeDepthSearch(p, 7, 1, true);

        System.out.println(b.toFind());
    }

    /**
     * Generates a corrected cube
     */
    public Cube getCorrectCube() {
        Row r0 = new Row("red", "red", "red");
        Row r1 = new Row("red", "red", "red");
        Row r2 = new Row("red", "red", "red");

        Face f0 = new Face(r0, r1, r2);

        r0 = new Row("white", "white", "white");
        r1 = new Row("white", "white", "white");
        r2 = new Row("white", "white", "white");

        Face f1 = new Face(r0, r1, r2);

        r0 = new Row("orange", "orange", "orange");
        r1 = new Row("orange", "orange", "orange");
        r2 = new Row("orange", "orange", "orange");

        Face f2 = new Face(r0, r1, r2);

        r0 = new Row("yellow", "yellow", "yellow");
        r1 = new Row("yellow", "yellow", "yellow");
        r2 = new Row("yellow", "yellow", "yellow");

        Face f3 = new Face(r0, r1, r2);

        r0 = new Row("green", "green", "green");
        r1 = new Row("green", "green", "green");
        r2 = new Row("green", "green", "green");

        Face f4 = new Face(r0, r1, r2);

        r0 = new Row("blue", "blue", "blue");
        r1 = new Row("blue", "blue", "blue");
        r2 = new Row("blue", "blue", "blue");

        Face f5 = new Face(r0, r1, r2);
        return new Cube(f0, f1, f2, f3, f4, f5);
    }
}
