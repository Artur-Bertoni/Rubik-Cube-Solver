# Rubik Cube Resolver

Program to solve a Rubik's Cube using search strategies in java.
At the moment the cube information is entered by changing the cube/cube.txt file;

You can choose between several search strategies, even by code, from the user interface class.
The solution presented is made up of the set of actions that must be done on the coordinates of the cube.

For example:

    X0 -> Y2 -> X0 -> Y0 -> Y1 -> Z2 -> Z2 

To carry out this operation, the physical cube must be placed with the red face facing the user and the white face on the right.
(The color of a face is indicated by the center square of the face.)
n€(0,1,2)

Carrying out Xn consists of moving a row of the red face to the right.

        Face 3:                                                  Face 0:
            ------------------------------------                   ------------------------------------
            green     || red       || orange   |                   white    || yellow   || yellow     |
            white     || yellow    || orange   |                   white    || red      || red        |
            orange    || orange    || yellow   |                   red      || red      || red        |
            ------------------------------------                   ------------------------------------

cube.X(0):

        Face 0:                                            Face 1:
            ------------------------------------           -----------------------------------
            green     || red       || orange   |           white     || yellow   || yellow   |
            white     || red       || red      |           white     || white    || orange   |
            red       || red       || red      |           white     || white    || orange   |
            ------------------------------------           -----------------------------------

Yn Consists of moving one column down:

            Face 0:
            ------------------------------------
            white     || yellow   || yellow    |
            white     || red      || red       |
            red       || red      || red       |
            ------------------------------------
            
cube.Y(2);

            Face 5:
            ------------------------------------
            blue      || blue      || yellow   |
            yellow    || blue      || red      |
            white     || green     || red      |
            ------------------------------------

Zn Transposes a face from the red face, in depth to the right, z0 transposes the red face towards the
right, z2 the orange face in the opposite direction.

            Face 2:
            ------------------------------------
            white     || green     || green    |
            yellow    || orange    || orange   |
            green     || blue      || yellow   |
            ------------------------------------

cube.Z(2);

            Face 2:
            ------------------------------------
            green     || yellow    || white    |
            blue      || orange    || green    |
            yellow    || orange    || green    |
            ------------------------------------

The objective of the program is from an initial status of the cube,
it will check if it is a status solution,

    if it is a solution, it returns a String with the steps until the solution.

    if it is not, it will generate the 9 possible moves and store them in a
    data structure (border) depending on the selected search strategy.

Repeat until solution or boundary is emptied.
To test the program:

    cd dist/

    java -jar resolveRubik.jar path_to_cube.txt
