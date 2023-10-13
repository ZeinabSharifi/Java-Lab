package model;

import java.util.ArrayList;

public class Point {
    private int number;
    private int pieceCount;
    private int color;
    ArrayList<Piece> checkers;

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
}
