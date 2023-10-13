package model;

public class Piece {

    private int atPoint;
    private int color;
    private boolean topChecker;
    private boolean inTray;
    private boolean onBar;
    private Double x;
    private Double y;

    public Piece(){

    }

    public Piece(int color, int atPoint, boolean topChecker) {
        this.topChecker = topChecker;
        this.inTray = false;
        this.onBar = false;
        this.color = color;
        this.atPoint = atPoint;
    }

    public int getAtPoint() {
        return atPoint;
    }

    public void setAtPoint(int atPoint) {
        this.atPoint = atPoint;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isTopChecker() {
        return topChecker;
    }

    public void setTopChecker(boolean topChecker) {
        this.topChecker = topChecker;
    }

    public boolean isInTray() {
        return inTray;
    }

    public void setInTray(boolean inTray) {
        this.inTray = inTray;
    }

    public boolean isOnBar() {
        return onBar;
    }

    public void setOnBar(boolean onBar) {
        this.onBar = onBar;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
