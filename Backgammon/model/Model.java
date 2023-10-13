package model;

import java.util.ArrayList;

public class Model {
    ArrayList<Point> board = new ArrayList<>();
    private Player playerOne; //black
    private Player playerTwo; //white
    private int doublingCube;
    private int type;

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
                        board.get(i-1).addChecker(new Piece(black,1,false));
                        board.get(i-1).addChecker(new Piece(black,1,true));
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
                            board.get(i-1).addChecker(new Piece(white,6,j==4));
                        }
                        break;
                    case 7:
                        board.add(new Point(7,0,noColor));
                        break;
                    case 8:
                        board.add(new Point(8,3,white));
                        for(int j=0;j<3;j++){
                            board.get(i-1).addChecker(new Piece(white,8,j==2));
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
                            board.get(i-1).addChecker(new Piece(black,12,j==4));
                        }
                        break;
                    case 13:
                        board.add(new Point(13,5,white));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(white,13,j==4));
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
                            board.get(i-1).addChecker(new Piece(black,17,j==2));
                        }
                        break;
                    case 18:
                        board.add(new Point(18,0,noColor));
                        break;
                    case 19:
                        board.add(new Point(19,5,black));
                        for(int j=0;j<5;j++){
                            board.get(i-1).addChecker(new Piece(black,19,j==4));
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
                        board.get(i-1).addChecker(new Piece(black,24,false));
                        board.get(i-1).addChecker(new Piece(black,24,true));
                        break;
                }
            }
        }



    public void setPlayer(int color, int type){

        playerOne.setPlayer( black,color==black,color==black );
        playerTwo.setPlayer(white,color==white,color==white);

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


    public boolean isPossibleMove(double currentX,double currentY,double destX,double destY){
        return false;
    }

    public void makeMove(){

    }


}
