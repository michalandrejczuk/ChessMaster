package org.example.chessMaster.client;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setUpBoardAndPieces();
        boolean gameOn = true;
        boolean gameWinner = false;
        while (gameOn && !gameWinner) {
         game.playerWhiteMove();
         game.playerBlackMove();
         gameWinner = game.CheckWinCondition();
         if (!gameWinner) gameOn = game.gameMenu();
        }

    }

}
