public class CheckersMove {

    int fromRow;
    int fromColumn;
    int toRow;
    int toColumn;

    //Objects of this class hold the properties of a move on the board.It also checks if the move is a jump or not

    public CheckersMove(int fromRow, int fromColumn, int toRow, int toColumn){
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;

    }

    public boolean isCapture(){
        boolean isCapture;
        isCapture = (fromRow - toRow == 2) || (fromRow - toRow == -2);
        return isCapture;
    }
}
