package org.example.chessMaster.core.model;

import java.util.Objects;

public abstract class Piece {

    private int coordinateX;
    private int coordinateY;
    private Color color;
    public Piece(int coordinateX, int coordinateY, Color color) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.color = color;
    }
    public Piece(String coordinates, Color color) {
        setCoordinates(coordinates);
        this.color = color;
    }
    public Piece() {
    }
    public void setCoordinates(String stringCoordinates) {
        char file = stringCoordinates.charAt(0);
        int rank = Integer.parseInt(Character.toString(stringCoordinates.charAt(1)));
        this.coordinateY = setYForFile(file);
        this.coordinateX = setXForRank(rank);
    }
    public String getStringCoordinates() {
        char letter = (char) (97 + this.coordinateY);
        int number = 8 - this.coordinateX;
        String l1 = Character.toString(letter);
        String l2 = String.valueOf(number);
        return l1.concat(l2);
    }
    public int setYForFile(char file) {
        return file - 97;
    }

    public int setXForRank(int rank) {
        return 8 - rank;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Color getColor() {
        return color;
    }

    protected int calcMoveWithDirectionFactor(int yOffset) {
        int dirFactor = color == Color.WHITE ? 1 : -1;
        return yOffset * dirFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return coordinateX == piece.coordinateX && coordinateY == piece.coordinateY && color == piece.color;
    }
    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY, color);
    }
    @Override
    public String toString() {
        return "Piece{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", color=" + color +
                '}';
    }
}
