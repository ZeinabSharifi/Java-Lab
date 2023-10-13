package model;

import java.util.ArrayList;

public class Point {
    private int number;
    private int pieceCount;
    private int color;
    ArrayList<Piece> checkers;
    private Double x1;
    private Double y1;
    private Double x2;
    private Double y2;

    public Point(){
        this.number = 0;
        this.pieceCount = 0;
        this.color = 0;

    }

    public Point(int number, int pieceCount, int color) {
        this.checkers = new ArrayList<>();
        this.pieceCount = pieceCount;
        this.number = number;
        this.color = color;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public ArrayList<Piece> getCheckers() {
        return checkers;
    }

    public void addChecker(Piece checker) {
        this.checkers.add(checker);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Double getX1() {
        return x1;
    }

    public Double getY1() {
        return y1;
    }

    public Double getX2() {
        return x2;
    }

    public Double getY2() {
        return y2;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public void setY1(Double y1) {
        this.y1 = y1;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public void setY2(Double y2) {
        this.y2 = y2;
    }
}
