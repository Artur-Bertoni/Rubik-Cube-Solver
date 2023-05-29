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
        return new Cube(c0, c1, c2, c3, c4, c5);
    }
}
