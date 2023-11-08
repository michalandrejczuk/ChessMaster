package org.example.chessMaster.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    private Piece cut = new Piece() {
    };

    @Test
    @DisplayName("setCoordinates test")
    public void test1() {
       cut.setCoordinates("a2");
       int x = cut.getCoordinateX();
       assertEquals(6, x);
    }

}