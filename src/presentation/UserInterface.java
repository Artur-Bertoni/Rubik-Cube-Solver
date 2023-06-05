package presentation;

import domain.Cube;
import domain.problems.ProblemA;
import domain.searches.IterativeDepthSearch;
import persistence.PersistenceOperations;

/**
 * A user interface
 */
public class UserInterface {

    public void menu(String dir) {

        Cube cube = PersistenceOperations.readCube(dir);

        ProblemA p = new ProblemA(cube);
        IterativeDepthSearch b = new IterativeDepthSearch(p, 7, 1, true);

        System.out.println(b.toFind());
    }
}
