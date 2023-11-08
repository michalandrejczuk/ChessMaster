package org.example.chessMaster.client;

import org.example.chessMaster.core.model.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game cut = new Game();

    @Test
    @DisplayName("setUpBoardAndPieces test")
    public void test1() {
        cut.setUpBoardAndPieces();
        Board board = cut.getBoard();
        assertNotNull(board);
    }
}