package model;


public class CheckersMove {
    private Point currentPoint;
    private Point destinationPoint;
    private Piece piece;
    private boolean tray;
    private boolean onBar;
    CheckersMove(){
        this.currentPoint=new Point();
        this.destinationPoint=new Point();
        this.piece=new Piece();
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Point getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(Point destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isTray() {
        return tray;
    }

    public void setTray(boolean tray) {
        this.tray = tray;
    }

    public boolean isOnBar() {
        return onBar;
    }

    public void setOnBar(boolean onBar) {
        this.onBar = onBar;
    }
}
