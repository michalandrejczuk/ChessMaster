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


//        Board board = new Board();
//        Pawn pawn1 = new Pawn("a2", Color.WHITE);
//        Pawn pawn2 = new Pawn("c2", Color.WHITE);
//        Pawn pawn3 = new Pawn("a7", Color.BLACK);
//        Pawn pawn4 = new Pawn("b7", Color.BLACK);
//        board.setPieceOnBoard(pawn1);
//        board.setPieceOnBoard(pawn2);
//        board.setPieceOnBoard(pawn3);
//        board.setPieceOnBoard(pawn4);
//        board.showBoard();
//        board.movePawn("a2", "a4");
//        board.movePawn("a7", "a5");
//        board.movePawn("c2", "c3");
//        board.movePawn("b7", "b5");
//        System.out.println();
//        board.showBoard();

//        Pawn[] enemyInTheRangeOfPawn = board.isEnemyInTheRangeOfPawn(pawn1);
//        board.checkAndHitTheEnemy(enemyInTheRangeOfPawn, pawn1);
//        enemyInTheRangeOfPawn = null;
//        board.movePawn("a4", "a5");
//        board.movePawn("c3", "c4");
//        enemyInTheRangeOfPawn = board.isEnemyInTheRangeOfPawn(pawn4);
//        board.checkAndHitTheEnemy(enemyInTheRangeOfPawn, pawn4);
//
//        board.showBoard();


    }

}
