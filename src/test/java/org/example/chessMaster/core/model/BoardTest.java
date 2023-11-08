package org.example.chessMaster.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board cut = new Board();

    @Test
    @DisplayName("setPieceOnBoard test")
    public void test1() {
        //given
        Piece piece = new Pawn("a2", Color.BLACK);
        //when
        cut.setPieceOnBoard(piece);
        int x = piece.getCoordinateX();
        int y = piece.getCoordinateY();
        //then
        assertEquals(piece, cut.getChessboard()[x][y]);
    }

    @Test
    @DisplayName("setAllPawnsOnBoard test")
    public void test2() {
        cut.setAllPawnsOnBoard();
        Color color1 = Color.BLACK;
        Color color = cut.getChessboard()[1][2].getColor();
        assertEquals(color1, color);
    }

    @Test
    @DisplayName("getPiece test")
    public void test3() {
        cut.setPieceOnBoard(new Pawn(2, 1, Color.BLACK));
        Pawn pawn = (Pawn) cut.getPiece(2, 1);
        Pawn pawn1 = new Pawn(2, 1, Color.BLACK);
        assertEquals(pawn1, pawn);

    }

    @Test
    @DisplayName("getPawnByChessMark test")
    public void test4() {
        Pawn pawn1 = new Pawn("b2", Color.WHITE);
        cut.setPieceOnBoard(pawn1);
        Pawn pawn = cut.getPawnByChessMark("b2");
        assertEquals(pawn1, pawn);
    }

    @Test
    @DisplayName("getCurrentPawn")
    public void test5() {
        Pawn pawn1 = new Pawn("b2", Color.WHITE);
        cut.setPieceOnBoard(pawn1);
        Pawn pawn = cut.getCurrentPawn("b2");
        assertEquals(pawn1, pawn);
    }

    @Test
    @DisplayName("movePawn test")
    public void test6() {
        Pawn pawn = new Pawn("d2", Color.WHITE);
        cut.setPieceOnBoard(pawn);
        cut.movePawn("d2", "d4");
        pawn = (Pawn) cut.getPiece(4,3);
        Pawn pawn1 = new Pawn("d4", Color.WHITE);
        assertEquals(pawn1, pawn);

    }
}