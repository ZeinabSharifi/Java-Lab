import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CheckersView extends JFrame  {

    private JButton newGameButton; //it is enabled during the game. whenever pressed, a new game starts
    private JLabel message; //interacting with players by showing warnings on this JLabel
    //show time elapsed after the new game
    private JLabel timeMin;
    private JLabel timeSec;
    //used to show each player's score
    private JLabel blackScore;
    private JLabel whiteScore;
    //
    private BoardPanel board;
    //used to show time elapsed
    static Timer timer;

    private int sec = 0;
    private int min = 0;





    public CheckersView(CheckersModel model) {

        super("Checkers");

        setLayout(null);
        setPreferredSize( new Dimension(700,500) );
        getContentPane().setBackground(new Color(32,178,170));

        newGameButton = new JButton("New Game");
        message = new JLabel("",JLabel.CENTER);
        message.setFont(new  Font("SanSerif", Font.BOLD, 16));
        message.setForeground(Color.BLACK);
        timeMin = new JLabel("",JLabel.CENTER);
        timeMin.setFont(new Font("SanSerif",Font.BOLD, 24));
        timeMin.setForeground(Color.BLACK);
        timeSec = new JLabel("",JLabel.LEFT);
        timeSec.setFont(new Font("SanSerif",Font.BOLD, 24));
        timeSec.setForeground(Color.BLACK);
        blackScore = new JLabel("",JLabel.CENTER);
        blackScore.setFont(new Font("SanSerif",Font.BOLD, 30));
        blackScore.setForeground(Color.BLACK);
        whiteScore = new JLabel("",JLabel.CENTER);
        whiteScore.setFont(new Font("SanSerif",Font.BOLD, 30));
        whiteScore.setForeground(Color.WHITE);



        timer = new Timer(1000, new ActionListener() { //add to second count after each 1000 milliseconds
            @Override
            public void actionPerformed(ActionEvent e) {//adjusting the view
                if(sec < 10) {
                    timeSec.setText(": 0" + String.valueOf(sec));
                }
                else{
                    timeSec.setText(": " + String.valueOf(sec));
                }
                if(min < 10) {
                    timeMin.setText("       0" + String.valueOf(min));
                }
                else{
                    timeMin.setText("       " + String.valueOf(min));
                }
                sec++;
                if (sec == 60) { //reset second count and add to minute count
                    sec = 0;
                    min += 1;
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

        board = new BoardPanel(model);
        //add all the items to the JFrame
        add(board);
        add(newGameButton);
        add(message);
        add(timeMin);
        add(timeSec);
        add(blackScore);
        add(whiteScore);

        //layout is chosen null
        board.setBounds(186,100,328,328);
        timeMin.setBounds(532, 225,75, 50 );
        timeSec.setBounds(607, 225,75, 50 );
        newGameButton.setBounds(18, 225, 150, 50);
        message.setBounds(100, 20, 500, 60);
        blackScore.setBounds(0,20,100,60);
        whiteScore.setBounds(600,20,100,60);
    }






    public class BoardPanel extends JPanel {

        private CheckersModel model;
        private boolean gameInProgress;
        //it can be eaither BLACK or WHITE
        private int player;
        //the location of the square the player has clicked on
        private int selectedRow;
        private int selectedColumn;
        private CheckersMove[] moves;

        public BoardPanel(CheckersModel model){
            this.model = model;
            setBackground(Color.BLACK);

            setNewGame();
        }

        public void setNewGame() {

            sec = 0;
            min = 0;
            timer.start();
            CheckersModel.whiteScore = 0;
            CheckersModel.blackScore = 0;
            player = CheckersModel.BLACK;
            moves = model.getMoves(CheckersModel.BLACK);//black starts the game. legal moves are stored in "moves"
            //it shows no square has been chosen yet at the beginning
            selectedRow = -1;
            selectedColumn = -1;
            message.setText("Black:  YOUR TURN! Select a checker.");
            whiteScore.setText(String.valueOf(CheckersModel.whiteScore));
            blackScore.setText(String.valueOf(CheckersModel.blackScore));
            gameInProgress = true;
            newGameButton.setEnabled(true);
            //repaint the components according to the current state of game
            repaint();
        }
        /*
        it gets a string to show the end of the game
         */
        void gameOver(String str){
            message.setText(str);
            newGameButton.setEnabled(true);
            gameInProgress = false;

        }
        /*
        * this method is called whenever a click is detected on the board with certain row & column.
        * it returns a certain move or null which will then be used in controller to update model & view
        *
        * */

        public CheckersMove clickSquare(int row, int column){
            //if one of the legal squares are selected by the player to start, set selectedRow and Column
            for(int i = 0; i< moves.length; i++){
                if(moves[i].fromRow == row && moves[i].fromColumn == column){
                    selectedRow = row;
                    selectedColumn = column;
                    if(player == CheckersModel.BLACK) {//ask player to choose a square to go to
                        message.setText("Black: Now make your move.");
                    }
                    else{
                        message.setText("White: Now make your move.");
                    }
                    repaint();
                    return null;
                }
            }

            //the player hasn't chosen a square yet or has chosen a wrong one
            if(selectedRow == -1 || selectedColumn == -1){
                if(player == CheckersModel.BLACK) {
                    message.setText("Black: First select a legal checker!");
                }
                else{
                    message.setText("White: First select a legal checker!");
                }
                return null;
            }
            //player has chosen a legal move
            //go through legal moves to find one with previous selected start and new selected destination
            //return the legal move to controller
            for(int i = 0; i<moves.length;i++){
                if(moves[i].fromRow == selectedRow && moves[i].fromColumn == selectedColumn
                        && moves[i].toRow == row && moves[i].toColumn == column){
                    return moves[i];

                }

            }
            // player hasn't chosen a legal square to move to yet
            message.setText("Select a legal square to move to from highlighted squares!");
            return null;

        }
        /**
         * this method is called in controller when a legal move is detected by clickSquare
         * it checks for further legal jumps, changes the player and checks if the game has ended or not
         */


        public void makeMove(CheckersMove move){


            if(move.isCapture()) {

                whiteScore.setText(String.valueOf(CheckersModel.whiteScore));
                blackScore.setText(String.valueOf(CheckersModel.blackScore));
                repaint();
                //updating moves in the new Square after the move is made by model
                moves = model.getMoreCapture(player, move.toRow, move.toColumn);
                if (moves != null) { //there is a possible capture in the new square
                    if (player == CheckersModel.BLACK)
                        message.setText("Black: Awesome! Keep Going.");
                    else
                        message.setText("White: Awesome! Keep Going.");
                    selectedRow = move.toRow; //there is only 1 square to start from
                    selectedColumn = move.toColumn;
                    repaint();
                    return;

                }
            }
                //end of current player's turn
            if(player == CheckersModel.BLACK){
                player = CheckersModel.WHITE;//change current player
                moves = model.getMoves(player);
                if(moves == null){// new player has no moves to make
                    gameOver("Game Over! Black wins.");
                    timer.stop();
                    return;
                }
                else if(moves[0].isCapture()){//player can jump
                    message.setText("White: Your TURN! Select a checker to Jump.");
                }
                else{//player has a normal move
                    message.setText("White: Your TURN! Select a checker.");
                }
            }
            else {
                player = CheckersModel.BLACK;
                moves = model.getMoves(player);
                if(moves == null){
                    gameOver("Game Over! White wins.");
                    timer.stop();
                    return;
                }
                else if(moves[0].isCapture()){
                    message.setText("Black: Your TURN! Select a checker to Jump.");
                }
                else{
                    message.setText("Black: Your TURN! Select a checker.");
                }
            }
            //after the player has changed these two are set to -1 to indicate no squares has been chosen yet
            selectedRow = -1;
            selectedColumn = -1;

            //if all the legal moves start from the same square, cset selectedRow & column to that
            if(moves != null) {
                boolean justOneChecker = true;
                for (int i = 1; i < moves.length; i++) {
                    if ( moves[i].fromRow != moves[0].fromRow
                            || moves[i].fromColumn != moves[0].fromColumn) {// a difference is found
                        justOneChecker = false;
                        break;
                    }
                }
                if (justOneChecker) {
                    selectedRow = moves[0].fromRow;
                    selectedColumn = moves[0].fromColumn;
                }
            }
            repaint();

        }

        @Override
        public void paintComponent(Graphics g){
            // 4 pixel border around BoardGame
            // using Graphics2D to get nicer circles
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLACK);
            for(int i = 0; i<4; i++){
                g.drawRect( 0+i, 0+i, getSize().width-1-i*2, getSize().height-1-i*2);
            }
            //drawing the squares and checkers on the board according to the board's status in model
            for(int i = 0; i<8; i++){
                for(int j = 0; j<8; j++){
                    if(i%2 == j%2)
                        g.setColor( new Color(0xBB8346));
                    else
                        g.setColor(new Color(0x523608));
                    g.fillRect(4+j*40, 4+i*40, 40, 40);
                    switch (model.getCheckerBoard()[i][j]){
                        case CheckersModel.black:
                            g.setColor(Color.BLACK);
                            g.fillOval(8+j*40, 8+i*40, 30, 30);
                            g.setColor(new Color(0x464646));
                            g.fillOval(10+j*40, 10+i*40, 25, 20);
                            break;
                        case CheckersModel.white:
                            g.setColor(new Color(0xBAC4D6));
                            g.fillOval(8+j*40, 8+i*40, 30, 30);
                            g.setColor(Color.WHITE);
                            g.fillOval(10+j*40, 10+i*40, 25, 22);
                            break;
                        case CheckersModel.blackKing:
                            g.setColor(Color.BLACK);
                            g.fillOval(8+j*40, 8+i*40, 30, 30);
                            g.setColor(new Color(55,62,94));
                            g.fillOval(10+j*40, 10+i*40, 25, 20);
                            g.setColor(Color.WHITE);
                            g.setFont(new Font("SanSerif", Font.BOLD, 20));
                            g.drawString("k", 20+j*40, 32+i*40);
                            break;
                        case CheckersModel.whiteKing:
                            g.setColor(new Color(0xBAC4D6));
                            g.fillOval(8+j*40, 8+i*40, 30, 30);
                            g.setColor(Color.WHITE);
                            g.fillOval(10+j*40, 10+i*40, 25, 22);
                            g.setColor(Color.BLACK);
                            g.setFont(new Font("SanSerif", Font.BOLD, 20));
                            g.drawString("k", 20+j*40, 32+i*40);
                            break;
                    }
                }
            }

            if(gameInProgress){
                //highlight the possible squares to start from
                g.setColor(Color.RED);
                for(int i=0;i<moves.length; i++){
                    for(int j = 0;j<4; j++) {
                        g.drawRect(4 +j+ moves[i].fromColumn * 40, 4 +j+ moves[i].fromRow * 40,
                                40 - 1 - 2 * j, 40 - 1 - 2 * j);
                    }
                }
                // a checker is chosen. Show possible moves for that checker. highlight the square itself
                if(selectedRow >=0 || selectedColumn >= 0){
                    //highlight selected square
                    g.setColor(Color.GREEN);
                    for(int j = 0;j<4; j++) {
                        g.drawRect(4+j + selectedColumn * 40, 4+j+selectedRow * 40,
                                40 - 1 - 2 * j, 40 - 1 - 2 * j);
                    }
                    //highlight possibles moves for that certain checker
                    g.setColor(Color.CYAN);
                    for(int i=0;i<moves.length; i++){
                        if(moves[i].fromRow == selectedRow && moves[i].fromColumn == selectedColumn) {
                            for (int j = 0; j < 4; j++) {
                                g.drawRect(4+j + moves[i].toColumn * 40, 4+j + moves[i].toRow * 40,
                                        40 - 1 - 2 * j, 40 - 1 - 2 * j);
                            }
                        }
                    }
                }
            }

        }

        public boolean getGameInProgress(){
            return gameInProgress;
        }





    }

    public void registerListener(CheckersController listener){

        newGameButton.addActionListener(listener);
        board.addMouseListener(listener);

    }

    public JButton getNewGameButton(){
        return newGameButton;
    }

    public BoardPanel getBoard() {
        return board;
    }

    public void setMessage(String str){
        message.setText(str);
    }
}
