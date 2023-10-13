
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class CheckersController implements ActionListener, MouseListener {
    private CheckersModel model;
    private CheckersView view;

    public CheckersController(CheckersModel model, CheckersView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getNewGameButton()) { //start a new game after the button is clicked
            model.setUpBoard();
            view.timer.stop();
            view.getBoard().setNewGame();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (!view.getBoard().getGameInProgress())
            view.setMessage("Click \"New Game\" Button to start a new game.");
        else {
            //get the location of the click on board
            int column = (evt.getX() - 4) / 40;
            int row = (evt.getY() - 4) / 40;
            if (column >= 0 && column < 8 && row >= 0 && row < 8) {//if the click is on the boardPanel
                CheckersMove move = view.getBoard().clickSquare(row, column);
                if(move != null) {
                    model.moveChecker(move);
                    view.getBoard().makeMove(move);
                }


            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
