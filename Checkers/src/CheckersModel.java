import java.util.ArrayList;

public class CheckersModel {
    // constants that are used all over the code

    static final int
                empty = 0,
                black = 1,
                blackKing = 2,
                white = 3,
                whiteKing = 4;
    static final int
            BLACK = 5,
            WHITE = 6;


    static int blackScore = 0;
    static int whiteScore = 0;

    private int[][] checkerBoard;


    CheckersModel() {
        checkerBoard = new int[8][8];
        setUpBoard();
    }

    // it is called whenever a new game starts.
    public void setUpBoard(){
        for(int i=0; i<8; i++){
            for(int j= 0; j<8; j++){
                if(i%2 == j%2){
                    checkerBoard[i][j] = empty;
                }
                else{

                    if(i<3){
                        checkerBoard[i][j] = white;
                    }
                    else if(i>4){
                        checkerBoard[i][j] = black;
                    }
                    else{
                        checkerBoard[i][j] = empty;
                    }
                }

            }

        }
    }

    //it changes the board according to the given move. it is assumed that the move is legal
    public void moveChecker(CheckersMove move){
        checkerBoard[move.toRow][move.toColumn] = checkerBoard[move.fromRow][move.fromColumn];
        checkerBoard[move.fromRow][move.fromColumn] = empty;
        int checker = checkerBoard[move.toRow][move.toColumn];
        if(move.isCapture()){
            int jumpRow = (move.fromRow + move.toRow)/2;
            int jumpColumn = (move.fromColumn + move.toColumn)/2;
            checkerBoard[jumpRow][jumpColumn] = empty;
            if(checker == black)
                blackScore += 1;
            else
                whiteScore += 1;
        }
        if(checker ==  black && move.toRow == 0){
            checker = blackKing;
        }
        if(checker == white && move.toRow == 7){
            checker = whiteKing;
        }
        checkerBoard[move.toRow][move.toColumn] = checker;
    }
    /*it makes a list of all the possible moves a player have at each turn.
    it first checkes if there are any jumps since if there are any possible jumps, they should only be
    legal moves. Jumps are checked in 4 directions using method canJump.
    if no jumps where found, normal moves are checked using canMove method.
    finally, an array of type CheckersMove is returned.

     */
    public CheckersMove[] getMoves(int player){
        if(player != BLACK && player != WHITE)
            return null;
        int checkerKing,checker;
        if(player == BLACK) {
            checker = black;
            checkerKing = blackKing;
        }
        else{
            checker = white;
            checkerKing = whiteKing;
        }
        ArrayList<CheckersMove> moves = new ArrayList<>();

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){//checking all the board squares for the player's pieces
                if(checkerBoard[i][j] == checker || checkerBoard[i][j] == checkerKing){
                    if(canJump(player,i+0,j+0,i-1,j+1,i-2,j+2))//up right
                        moves.add(new CheckersMove(i+0,j+0,i-2,j+2));
                    if(canJump(player, i+0,j+0, i-1,j-1,i-2,j-2))//up left
                        moves.add(new CheckersMove(i+0, j+0, i-2, j-2));
                    if(canJump(player,i+0,j+0,i+1,j+1,i+2,j+2)) //down right
                        moves.add(new CheckersMove(i+0,j+0,i+2,j+2));
                    if(canJump(player,i+0,j+0,i+1,j-1,i+2,j-2)) // down left
                        moves.add(new CheckersMove(i+0,j+0,i+2,j-2));
                }
            }
        }

        if(moves.size() == 0){//no jumps were possible
            for(int i = 0; i<8; i++){
                for(int j = 0; j<8; j++){//look for possible normal moves
                    if(checkerBoard[i][j] == checker || checkerBoard[i][j] == checkerKing){
                        if(canMove(player,i,j,i-1,j+1))//up right
                            moves.add(new CheckersMove(i+0,j+0,i-1,j+1));
                        if(canMove(player, i,j, i-1,j-1))//up left
                            moves.add(new CheckersMove(i+0, j+0, i-1, j-1));
                        if(canMove(player,i,j,i+1,j+1)) //down right
                            moves.add(new CheckersMove(i+0,j+0,i+1,j+1));
                        if(canMove(player,i,j,i+1,j-1)) // down left
                            moves.add(new CheckersMove(i+0,j+0,i+1,j-1));
                    }
                }
            }

        }

        if(moves.size() == 0){ // neither a jump nor a move was legal
            return null;
        }
        else{// either jump or move was legAL
            CheckersMove[] allMoves = new CheckersMove[moves.size()];
            for(int i=0; i<allMoves.length; i++) {//make an array of all legal moves(either just jumps or just moves)
                allMoves[i] = moves.get(i);
            }
            return allMoves;

        }

    }
    /**
     * this method is called when a player has jumped in the current move. it checks if there are
     * any other jumps in the new position. the logic is pretty much the same as getMoves
     *
     */


    public CheckersMove[] getMoreCapture(int player, int row, int col){

        if(player != BLACK && player != WHITE)
            return null;
        int checkerKing,checker;
        if(player == BLACK) {
            checker = black;
            checkerKing = blackKing;
        }
        else{
            checker = white;
            checkerKing = whiteKing;
        }
        ArrayList<CheckersMove> moves = new ArrayList<>();
        if(checkerBoard[row][col] == checker || checkerBoard[row][col] == checkerKing){
            if(canJump(player,row+0,col+0,row-1,col+1,row-2,col+2))
                //up right
                moves.add(new CheckersMove(row+0,col+0,row-2,col+2));
            if(canJump(player, row+0,col+0, row-1,col-1,row-2,col-2))
                //up left
                moves.add(new CheckersMove(row+0, col+0, row-2, col-2));
            if(canJump(player,row+0,col+0,row+1,col+1,row+2,col+2))
                //down right
                moves.add(new CheckersMove(row+0,col+0,row+2,col+2));
            if(canJump(player,row+0,col+0,row+1,col-1,row+2,col-2))
                // down left
                moves.add(new CheckersMove(row+0,col+0,row+2,col-2));
        }

        if(moves.size() == 0) {
            return null;
        }
        else{
            CheckersMove[] allMoves = new CheckersMove[moves.size()];
            for(int i = 0; i<allMoves.length; i++)
                allMoves[i] = moves.get(i);
            return allMoves;
        }

    }
    /**
     * the next two methods checkhe possibility of a jump or a move
     * in a certain diagonal direction and returns a booleen
     *
     */

    boolean canJump(int player, int fromRow, int fromCol, int midRow, int midCol, int toRow, int toCol){

        if(toRow >=8 || toRow <0 || toCol >= 8 || toCol <0)//not on board
            return false;
        if(checkerBoard[toRow][toCol] != empty)//not empty
            return false;
        if(player == BLACK){
            if(toRow > fromRow && checkerBoard[fromRow][fromCol] == black)//normal black can move up only
                return false;
            if(!(checkerBoard[midRow][midCol] == white || checkerBoard[midRow][midCol] == whiteKing))
                return false; // there isn't a white piece in the middle
            return true;
        }
        else{
            if(toRow < fromRow && checkerBoard[fromRow][fromCol] == white)//normal white can move down only
                return false;
            if(!(checkerBoard[midRow][midCol] == black || checkerBoard[midRow][midCol] == blackKing))
                return false; // there isn't a black piece in the middle
            return true;
        }

    }

    boolean canMove(int player, int fromRow, int fromCol, int toRow, int toCol){

        if(toRow >= 8 || toRow < 0 || toCol >= 8 || toCol <0)//not on board
            return false;
        if(checkerBoard[toRow][toCol] != empty)// not empty
            return false;
        if(player == BLACK){
            if(toRow > fromRow && checkerBoard[fromRow][fromCol] == black)//normal black only move up
                return false;
            return true;
        }
        else{
            if(toRow < fromRow && checkerBoard[fromRow][fromCol] == white)//normal white only move down
                return false;
            return true;
        }
    }
    /*

     */

    public int[][] getCheckerBoard(){
        return checkerBoard;
    }


}

