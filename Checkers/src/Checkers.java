import javax.swing.*;
/*
* This java code lets two players play CHECKERS.
*/
/*
*RULES:
* Black always starts the game.
* A normal checker can only make one diagonal move at a time
* Black moves up, while white moves down
* If a there is a chance to capture the opponent's checker, the player MUST take it.
* Jumping will continue until there are no more jumps
* When reached to the opponent's last row, the checker will become king.
* A King can move in 4 directions freely.(still just one square at a time)
* The game ends when there are no moves or the there are no checkers left
*
 */

/*
* The program benefits from the MVC Pattern design.
* Logic of the game and the view and graphics are tried to function separately.
* Updating both model and view are done in controller which is the interface between these two.
* All JComponents are set in view and their listeners are registered. Handling the actions is done in controller.
* Method main creates objects of model,view, controller and they are passed to each other. Listeners are registered
* and finally the designed window
*
* */
public class Checkers {
    public static void main(String[] args) {

        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(model);
        CheckersController controller = new CheckersController(model, view);

        view.registerListener(controller);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();
        view.setVisible(true);
        view.setResizable(false);


    }

}
