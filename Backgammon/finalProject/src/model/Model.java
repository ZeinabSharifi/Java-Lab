package model;

import java.util.ArrayList;

public class Model {
    ArrayList<Point> board = new ArrayList<>();
    private Player playerOne; //black
    private Player playerTwo; //white
    ArrayList<Piece> playerOneOnBar = new ArrayList<>();
    ArrayList<Piece> playerTwoOnBar = new ArrayList<>();
    private int doublingCube;
    private int type;
    private int numberOfSets;
    private double turnTime;
    private int turn;
    private int playerOneOnBarCount = 0;
    private int playerTwoOnBarCount = 0;
    private int homeNumberWhite=0;
    private int homeNumberBlack=0;


    public static final int
            noColor = 0,
            black = 1,
            white = 2,
            type1 = 3,
            type2 = 4,
            clockwise = 5,
            counterClockwise = 6;


    public Model(int type, int color) {

        playerTwo = new Player();
        playerOne = new Player();
        this.doublingCube = 1;
        this.type=type;
        setPlayer(color,type);
        setUpBoard();
    }



        public void setUpBoard(){
            for(int i=1; i<25;i++) {
                switch (i) {
                    case 1:
                        board.add(new Point(1,2,black));
                        board.get(i-1).addChecker(new Piece(black,1,false,false));
                        board.get(i-1).addChecker(new Piece(black,1,true,false));
                        //-----------------------------
                        System.out.println(board.get(i-1).getColor());
                        break;
                    case 2:
                        board.add(new Point(2,0,noColor));
                        break;
                    case 3:
                        board.add(new Point(3,0,noColor));
                        break;
                    case 4:
                        board.add(new Point(4,0,noColor));
                        break;
                    case 5:
                        board.add(new Point(5,0,noColor));
                        break;
                    case 6:
                        board.add(new Point(6,5,white));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(white,6,j==4,false));
                        }
                        break;
                    case 7:
                        board.add(new Point(7,0,noColor));
                        break;
                    case 8:
                        board.add(new Point(8,3,white));
                        for(int j=0;j<3;j++){
                            board.get(i-1).addChecker(new Piece(white,8,j==2,false));
                        }

                        break;
                    case 9:
                        board.add(new Point(9,0,noColor));
                        break;
                    case 10:
                        board.add(new Point(10,0,noColor));
                        break;
                    case 11:
                        board.add(new Point(11,0,noColor));
                        break;
                    case 12:
                        board.add(new Point(12,5,black));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(black,12,j==4,false));
                        }
                        break;
                    case 13:
                        board.add(new Point(13,5,white));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(white,13,j==4,false));
                        }
                        break;
                    case 14:
                        board.add(new Point(14,0,noColor));
                        break;
                    case 15:
                        board.add(new Point(15,0,noColor));
                        break;
                    case 16:
                        board.add(new Point(16,0,noColor));
                        break;
                    case 17:
                        board.add(new Point(17,3,black));
                        for(int j=0;j<3;j++){
                            board.get(i-1).addChecker(new Piece(black,17,j==2,false));
                        }
                        break;
                    case 18:
                        board.add(new Point(18,0,noColor));
                        break;
                    case 19:
                        board.add(new Point(19,5,black));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(black,19,j==4,false));
                        }
                        break;
                    case 20:
                        board.add(new Point(20,0,noColor));
                        break;
                    case 21:
                        board.add(new Point(21,0,noColor));
                        break;
                    case 22:
                        board.add(new Point(22,0,noColor));
                        break;
                    case 23:
                        board.add(new Point(23,0,noColor));
                        break;
                    case 24:
                        board.add(new Point(24,2,white));
                        board.get(i-1).addChecker(new Piece(white,24,false,false));
                        board.get(i-1).addChecker(new Piece(white,24,true,false));
                        //-----------------------------
                        System.out.println(board.get(i-1).getColor());
                        break;
                }
            }
        }



    public void setPlayer(int color, int type){
            int c1,c2;
            if (color==Model.white){
                c1=Model.white;
                c2=Model.black;
            }else {
                c1=Model.black;
                c2=Model.white;
            }
            if(turn==Model.black){

                playerOne.setPlayer( c1,true,true );
                playerTwo.setPlayer(c2,false,false);

            }else{
                playerOne.setPlayer( c2,false,false );
                playerTwo.setPlayer(c1,true,true);
            }


    }


    public ArrayList<Point> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Point> board) {
        this.board = board;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public boolean isPossibleMove(double currentX, double currentY, double destX, double destY){
        return false;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(int numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public double getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(double turnTime) {
        this.turnTime = turnTime;
    }

    public int checkName(String name1, String name2){
        if(name1.equals("") && name2.equals("")){
            return 3;
        }else if(name1.equals("")){
            return 1;
        }else if(name2.equals("")){
            return 2;
        }
        return 0;
    }

    public int whichPlayerStart(int die1,int die2){
        if (die1>die2){
            return 1;
        }else {
            return 2;
        }

    }

    public void makeMove(Point origin,Point destination,Piece piece){//to update model according to a certain move.
        //it is assumed that the move is legal
        //update points///
        //update points///
        /*update origin*/
      /*  if(origin!=null) {//the move was from a triangle
            origin.getCheckers().remove(origin.getPieceCount() - 1);
            origin.setPieceCount(origin.getPieceCount() - 1);
            if (origin.getPieceCount() != 0) {
                origin.getCheckers().get(origin.getPieceCount() - 1).setTopChecker(true);
            } else {
                origin.setColor(Model.noColor);//when origin is empty
            }
        }else{//the move was from bar
            if(piece.getColor()==playerOne.getColor()){//a piece on bar from player one is removed
                playerOneOnBar.remove(playerOneOnBarCount-1);
                playerOneOnBarCount--;
                playerOne.setPieceBarCount(playerOneOnBarCount);
            }else{
                playerTwoOnBar.remove(playerTwoOnBarCount-1);
                playerTwoOnBarCount--;
                playerTwo.setPieceBarCount(playerTwoOnBarCount);
            }



        }
        System.out.println("--------------origin:"+origin.getColor());
        //update destination
        if(destination!=null) {//destination is a triangle
            if (destination.getColor() != piece.getColor() && destination.getColor() != Model.noColor) {//a checker is going to get hit

                // TODO: 7/6/2019  piece on bar for each player should get updated
                //destination.getCheckers().get(0).setTopChecker(false);
                if (destination.getColor() == playerOne.getColor()) {//one of player one's checkers got hit
                    playerOneOnBar.add(new Piece(playerOne.getColor(), 0, false,true));
                    //playerOneOnBar.get(playerOneOnBarCount).setOnBar(true);
                    playerOneOnBarCount++;
                    playerOne.setPieceBarCount(playerOneOnBarCount);
                } else {//one of player two's checkers got hit
                    playerTwoOnBar.add(new Piece(playerTwo.getColor(), 0, false,true));
                    //playerTwoOnBar.get(playerTwoOnBarCount).setOnBar(true);
                    playerTwoOnBarCount++;
                    playerTwo.setPieceBarCount(playerTwoOnBarCount);
                }
                destination.getCheckers().remove(0);

            } else {//usual move from one triangle to another with the same color
                if(destination.getPieceCount()!=0) {
                    destination.getCheckers().get(destination.getPieceCount() - 1).setTopChecker(false);
                }
                destination.setPieceCount(destination.getPieceCount() + 1);
            }
            System.out.println("piece go to destination with color :  " +piece.getColor() +"\n");
            destination.getCheckers().add(piece);
            destination.setColor(piece.getColor());
            //update certain piece//
            piece.setAtPoint(destination.getNumber());
            System.out.println("destination color: "+destination.getColor());

        }else{//destination is Tray

        }*/
        //the X and Y are updated in View
       /* origin.getCheckers().remove(origin.getPieceCount()-1);
        origin.setPieceCount(origin.getPieceCount()-1);
        if(origin.getPieceCount()!=0) {
            origin.getCheckers().get(origin.getPieceCount() - 1).setTopChecker(true);
        }else{
            origin.setColor(Model.noColor);//when origin is empty
        }

        if(destination.getColor() != piece.getColor() && destination.getColor() != Model.noColor){
            // TODO: 7/6/2019  piece on bar for each player should get updated
            //destination.getCheckers().get(0).setTopChecker(false);
            destination.getCheckers().remove(0);
        }
        else{
            destination.setPieceCount(destination.getPieceCount()+1);
        }
        destination.getCheckers().add(piece);
        destination.setColor(piece.getColor());
        //update certain piece//
        piece.setAtPoint(destination.getNumber());*/
        if(origin!=null) {//the move was from a triangle
            origin.getCheckers().remove(origin.getPieceCount() - 1);
            origin.setPieceCount(origin.getPieceCount() - 1);
            if (origin.getPieceCount() != 0) {
                origin.getCheckers().get(origin.getPieceCount() - 1).setTopChecker(true);
            } else {
                origin.setColor(Model.noColor);//when origin is empty
            }
        }else{//the move was from bar
            if(piece.getColor()==playerOne.getColor()){//a piece on bar from player one is removed
                playerOneOnBar.remove(playerOneOnBarCount-1);
                playerOneOnBarCount--;
                playerOne.setPieceBarCount(playerOneOnBarCount);
            }else{
                playerTwoOnBar.remove(playerTwoOnBarCount-1);
                playerTwoOnBarCount--;
                playerTwo.setPieceBarCount(playerTwoOnBarCount);
            }

        }
        //update destination
        if(destination!=null) {//destination is a triangle
            if(destination.getColor() != Model.noColor) {
                if (destination.getColor() != piece.getColor()) {//a checker is going to get hit

                    // TODO: 7/6/2019  piece on bar for each player should get updated
                    //destination.getCheckers().get(0).setTopChecker(false);
                    if (destination.getColor() == playerOne.getColor()) {//one of player one's checkers got hit
                        playerOneOnBar.add(new Piece(playerOne.getColor(), 0, false, true));
                        //playerOneOnBar.get(playerOneOnBarCount).setOnBar(true);
                        playerOneOnBarCount++;
                        playerOne.setPieceBarCount(playerOneOnBarCount);
                    } else {//one of player two's checkers got hit
                        playerTwoOnBar.add(new Piece(playerTwo.getColor(), 0, false, true));
                        //playerTwoOnBar.get(playerTwoOnBarCount).setOnBar(true);
                        playerTwoOnBarCount++;
                        playerTwo.setPieceBarCount(playerTwoOnBarCount);
                    }
                    destination.getCheckers().remove(0);

                }else{
                    destination.getCheckers().get(destination.getPieceCount() - 1).setTopChecker(false);
                    destination.getCheckers().add(piece);
                    destination.setPieceCount(destination.getPieceCount() + 1);
                }
            }else {//usual move from one triangle to another with the same color
                destination.setColor(piece.getColor());
                destination.getCheckers().add(piece);
                destination.setPieceCount(destination.getPieceCount() + 1);

            }
            /*if(piece.getColor()==Model.black){
                piece.setColor(Model.white);
            }else{
                piece.setColor(Model.black);
            }*/
            System.out.println("piece go to destination with color :  " +piece.getColor() +"\n");
            destination.getCheckers().add(piece);
            destination.setColor(piece.getColor());
            //update certain piece//
            piece.setAtPoint(destination.getNumber());
            System.out.println("destination color: "+destination.getColor());

        }else{//destination is Tray

        }


    }



    public ArrayList<CheckersMove> getMoves(int die,int dice1,int dice2){    //checks every point to find possible moves according to the turn of
        ArrayList<CheckersMove> possibleCheckers=new ArrayList<>();         //player,color and number of die;
        int number;
        if(playerOne.isTurn() && playerOneOnBar.size()!=0){
            int size=playerOneOnBar.size();
            for (int i=0;i<size;i++){
                if (playerOne.getColor()==white ) {
                    if(board.get(24-dice1).getCheckers().size()==0 || (board.get(24-dice1).getCheckers().size()==1 && board.get(24-dice1).getColor()==black )){
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength=possibleCheckers.size();
                        possibleCheckers.get(arrayLength-1).setPiece(playerOneOnBar.get(i));
                        possibleCheckers.get(arrayLength-1).setOnBar(true);
                        possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(24-dice1));
                    }else if(board.get(24-dice2).getCheckers().size()==0 || (board.get(24-dice2).getCheckers().size()==1 && board.get(24-dice2).getColor()==black)){
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength=possibleCheckers.size();
                        possibleCheckers.get(arrayLength-1).setPiece(playerOneOnBar.get(i));
                        possibleCheckers.get(arrayLength-1).setOnBar(true);
                        possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(24-dice2));
                    }
                }else if(playerOne.getColor()==black) {
                    if(board.get(dice1).getCheckers().size()==0 || (board.get(dice1).getCheckers().size()==1 && board.get(dice1).getColor()==white )){
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength=possibleCheckers.size();
                        possibleCheckers.get(arrayLength-1).setPiece(playerOneOnBar.get(i));
                        possibleCheckers.get(arrayLength-1).setOnBar(true);
                        possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(dice1));
                    }else if(board.get(dice2).getCheckers().size()==0 || (board.get(dice2).getCheckers().size()==1 && board.get(dice2).getColor()==white)){
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength=possibleCheckers.size();
                        possibleCheckers.get(arrayLength-1).setPiece(playerOneOnBar.get(i));
                        possibleCheckers.get(arrayLength-1).setOnBar(true);
                        possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(dice2));
                    }

                }

            }
            return possibleCheckers;
        }

        if((playerTwo.isTurn() && playerTwoOnBar.size()!=0)){
            int size=playerTwoOnBar.size();
            for (int i=0;i<size;i++) {
                if (playerTwo.getColor() == white) {
                    if (board.get(24 - dice1).getCheckers().size() == 0 || (board.get(24 - dice1).getCheckers().size() == 1 && board.get(24 - dice1).getColor() == black)) {
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength = possibleCheckers.size();
                        possibleCheckers.get(arrayLength - 1).setPiece(playerTwoOnBar.get(i));
                        possibleCheckers.get(arrayLength - 1).setOnBar(true);
                        possibleCheckers.get(arrayLength - 1).setDestinationPoint(board.get(24 - dice1));
                    } else if (board.get(24 - dice2).getCheckers().size() == 0 || (board.get(24 - dice2).getCheckers().size() == 1 && board.get(24 - dice2).getColor() == black)) {
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength = possibleCheckers.size();
                        possibleCheckers.get(arrayLength - 1).setPiece(playerTwoOnBar.get(i));
                        possibleCheckers.get(arrayLength - 1).setOnBar(true);
                        possibleCheckers.get(arrayLength - 1).setDestinationPoint(board.get(24 - dice2));
                    }
                } else if (playerTwo.getColor() == black) {
                    if (board.get(dice1).getCheckers().size() == 0 || (board.get(dice1).getCheckers().size() == 1 && board.get(dice1).getColor() == white)) {
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength = possibleCheckers.size();
                        possibleCheckers.get(arrayLength - 1).setPiece(playerTwoOnBar.get(i));
                        possibleCheckers.get(arrayLength - 1).setOnBar(true);
                        possibleCheckers.get(arrayLength - 1).setDestinationPoint(board.get(dice1));
                    } else if (board.get(dice2).getCheckers().size() == 0 || (board.get(dice2).getCheckers().size() == 1 && board.get(dice2).getColor() == white)) {
                        possibleCheckers.add(new CheckersMove());
                        int arrayLength = possibleCheckers.size();
                        possibleCheckers.get(arrayLength - 1).setPiece(playerTwoOnBar.get(i));
                        possibleCheckers.get(arrayLength - 1).setOnBar(true);
                        possibleCheckers.get(arrayLength - 1).setDestinationPoint(board.get(dice2));
                    }
                }
            }
                return possibleCheckers;
        }

        for (int i=0;i<24;i++){
            number=board.get(i).getPieceCount();
            if (number!=0){
                if((playerOne.isTurn() && playerOne.getColor()==board.get(i).getColor()) || (playerTwo.isTurn() && playerTwo.getColor()==board.get(i).getColor())){
                    if(board.get(i).getColor()==white){//white will always go down in numbers.
                        if ((i+1)<=die){
                            if (homeNumberWhite>16){
                                possibleCheckers.add(new CheckersMove());
                                int arrayLength=possibleCheckers.size();
                                possibleCheckers.get(arrayLength-1).setPiece(board.get(i).getCheckers().get(number-1));
                                possibleCheckers.get(arrayLength-1).setTray(true);
                                possibleCheckers.get(arrayLength-1).setCurrentPoint(board.get(i));
                                possibleCheckers.get(arrayLength-1).setDestinationPoint(null);
                            }

                        }else if (board.get(i-die).getPieceCount()==0 || board.get(i-die).getColor()==white || (board.get(i-die).getColor()==black && board.get(i-die).getPieceCount()==1)){
                            possibleCheckers.add(new CheckersMove());
                            int arrayLength=possibleCheckers.size();
                            possibleCheckers.get(arrayLength-1).setPiece(board.get(i).getCheckers().get(number-1));
                            possibleCheckers.get(arrayLength-1).setTray(false);
                            possibleCheckers.get(arrayLength-1).setCurrentPoint(board.get(i));
                            possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(i-die));
                        }
                    }else {
                        if ((24-i)<=die){
                            if (homeNumberBlack>16){
                                possibleCheckers.add(new CheckersMove());
                                int arrayLength=possibleCheckers.size();
                                possibleCheckers.get(arrayLength-1).setPiece(board.get(i).getCheckers().get(number-1));
                                possibleCheckers.get(arrayLength-1).setTray(true);
                                possibleCheckers.get(arrayLength-1).setCurrentPoint(board.get(i));
                                possibleCheckers.get(arrayLength-1).setDestinationPoint(null);
                            }

                        }else if (board.get(i+die).getPieceCount()==0 || board.get(i+die).getColor()==black || (board.get(i+die).getColor()==white && board.get(i+die).getPieceCount()==1)){
                            possibleCheckers.add(new CheckersMove());
                            int arrayLength=possibleCheckers.size();
                            possibleCheckers.get(arrayLength-1).setPiece(board.get(i).getCheckers().get(number-1));
                            possibleCheckers.get(arrayLength-1).setTray(false);
                            possibleCheckers.get(arrayLength-1).setCurrentPoint(board.get(i));
                            possibleCheckers.get(arrayLength-1).setDestinationPoint(board.get(i+die));
                        }

                    }
                }
            }
        }
        return possibleCheckers;
    }

    public void changeTurn(){
        if(playerTwo.isTurn()){
            playerOne.setTurn(true);
            playerTwo.setTurn(false);
        }else {
            playerOne.setTurn(false);
            playerTwo.setTurn(true);
        }
    }

    public ArrayList<Piece> getPlayerOneOnBar() {
        return playerOneOnBar;
    }

    public void setPlayerOneOnBar(ArrayList<Piece> playerOneOnBar) {
        this.playerOneOnBar = playerOneOnBar;
    }

    public ArrayList<Piece> getPlayerTwoOnBar() {
        return playerTwoOnBar;
    }

    public void setPlayerTwoOnBar(ArrayList<Piece> playerTwoOnBar) {
        this.playerTwoOnBar = playerTwoOnBar;
    }

    public int getPlayerOneOnBarCount() {
        return playerOneOnBarCount;
    }

    public void setPlayerOneOnBarCount(int playerOneOnBarCount) {
        this.playerOneOnBarCount = playerOneOnBarCount;
    }

    public int getPlayerTwoOnBarCount() {
        return playerTwoOnBarCount;
    }

    public void setPlayerTwoOnBarCount(int playerTwoOnBarCount) {
        this.playerTwoOnBarCount = playerTwoOnBarCount;
    }

    public int getHomeNumberWhite() {
        return homeNumberWhite;
    }

    public void setHomeNumberWhite(int homeNumberWhite) {
        this.homeNumberWhite = homeNumberWhite;
    }

    public int getHomeNumberBlack() {
        return homeNumberBlack;
    }

    public void setHomeNumberBlack(int homeNumberBlack) {
        this.homeNumberBlack = homeNumberBlack;
    }
}
