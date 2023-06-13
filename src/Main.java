import presentation.UserInterface;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        //Can be loaded as a position parameter of the file containing the cube
        if (args.length == 1) {
            ui.menu(args[0]);

        } else {
            ui.menu("cube/cube.txt");
        }
    }
}
