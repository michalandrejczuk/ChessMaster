package org.example.chessMaster.core.model;

public class Pawn extends Piece {

    private boolean hasMoved;

    public Pawn(int coordinateX, int coordinateY, Color color) {
        super(coordinateX, coordinateY, color);
    }

    public Pawn(String coordinates, Color color) {
        super(coordinates, color);
    }
    public Pawn() {

    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }



    @Override
    public String toString() {
        return "Pawn{} " + super.toString();
    }
}
