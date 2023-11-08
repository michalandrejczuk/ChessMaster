package org.example.chessMaster.client;

import org.example.chessMaster.core.model.*;

import java.util.Optional;
import java.util.Scanner;

public class Game {
    private PlayerWhite playerWhite = new PlayerWhite();
    private PlayerBlack playerBlack = new PlayerBlack();

    private Board board;
    private Scanner scanner = new Scanner(System.in);

    public void setUpBoardAndPieces() {
        board = new Board();
        board.setAllPawnsOnBoard();
        board.showBoard();
    }
    public void playerWhiteMove() {
        System.out.println("----------------");
        System.out.println("RUCH BIALYCH:");
        String currentCoordinates = playerWhite.setCurrentField();
        String requiredCoordinates = playerWhite.setTargetField();
        Pawn currentPawn = board.getCurrentPawn(currentCoordinates);
        if (currentPawn.getColor() == Color.WHITE) {
            Pawn[] enemyInTheRangeOfPawn = board.isEnemyInTheRangeOfPawn(currentPawn);
            if (enemyInTheRangeOfPawn[0] == null && enemyInTheRangeOfPawn[1] == null) {
                board.movePawn(currentCoordinates, requiredCoordinates);
                return;
            }
            board.checkAndHitTheEnemy(enemyInTheRangeOfPawn, currentPawn, currentCoordinates);
        } else {
            System.out.println("Mylnie wybrany czarny pionek");
            playerWhiteMove();

        }
    }
    public void playerBlackMove() {
        System.out.println("RUCH CZARNYCH:");
        String currentCoordinates = playerBlack.setCurrentField();
        String requiredCoordinates = playerBlack.setTargetField();
        Pawn currentPawn = board.getCurrentPawn(currentCoordinates);
        if (currentPawn.getColor() == Color.BLACK) {
            Pawn[] enemyInTheRangeOfPawn = board.isEnemyInTheRangeOfPawn(currentPawn);
            if (enemyInTheRangeOfPawn[0] == null && enemyInTheRangeOfPawn[1] == null) {
                board.movePawn(currentCoordinates, requiredCoordinates);
                return;
            }
            board.checkAndHitTheEnemy(enemyInTheRangeOfPawn, currentPawn, currentCoordinates);
        } else {
            System.out.println("Mylnie wybrany bialy pionek");
            playerBlackMove();
        }
    }
    public void showBoard() {
       board.showBoard();
    }

    public Board getBoard() {
        return board;
    }

    public boolean CheckWinCondition() {
        Piece[][] chessboard = this.board.getChessboard();
        Optional<Piece> pieceOptional;
        for (Piece[] squares : chessboard) {
            for (Piece square : squares) {
                pieceOptional = Optional.ofNullable(square);
                if (pieceOptional.isPresent()) {
                    boolean rangeOfWinControl = square.getCoordinateX() == 0 || square.getCoordinateX() == 7;
                    if (rangeOfWinControl && pieceOptional.isPresent()) {
                        String winner = square.getColor() == Color.WHITE ? "WHITE WIN" : "BLACK WIN";
                        System.out.printf("GAME OVER!!! %s%n", winner);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean gameMenu() {
        System.out.println("Opcje MENU: ");
        System.out.println("\"exit\" -> wyjscie");
        System.out.println("\"s\" -> pokaz plansze");
        System.out.println("dowolny znak -> gramy dalej");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("exit")) {
            return false;
        }
        if (answer.equalsIgnoreCase("s")) {
            showBoard();
        }  return true;

    }
}
