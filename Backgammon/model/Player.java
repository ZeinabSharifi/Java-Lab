package model;

public class Player {
    private int color;
    private int winSetCount;
    private int pieceBarCount;
    private int pieceTrayCount;
    private int dieSum;
    private boolean canDouble;
    private boolean isTurn;
    //private int clockWise;
    private Piece[] pieces;


    public Player() {
        this.winSetCount = 0;
        this.pieceBarCount = 0;
        this.pieceTrayCount = 0;
        this.dieSum = 0;
        this.pieces = new Piece[15];
    }

    public void setPlayer(int color, boolean canDouble, boolean isTurn/*, int clockWise*/) {
        this.isTurn = isTurn;
        this.color = color;
        this.canDouble = canDouble;
        //this.clockWise = clockWise;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getWinSetCount() {
        return winSetCount;
    }

    public void setWinSetCount(int winSetCount) {
        this.winSetCount = winSetCount;
    }

    public int getPieceBarCount() {
        return pieceBarCount;
    }

    public void setPieceBarCount(int pieceBarCount) {
        this.pieceBarCount = pieceBarCount;
    }

    public int getPieceTrayCount() {
        return pieceTrayCount;
    }

    public void setPieceTrayCount(int pieceTrayCount) {
        this.pieceTrayCount = pieceTrayCount;
    }

    public int getDieSum() {
        return dieSum;
    }

    public void setDieSum(int dieSum) {
        this.dieSum = dieSum;
    }

    public boolean isCanDouble() {
        return canDouble;
    }

    public void setCanDouble(boolean canDouble) {
        this.canDouble = canDouble;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }
}
