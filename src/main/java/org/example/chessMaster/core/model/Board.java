package org.example.chessMaster.core.model;

import java.util.Scanner;

public class Board {
    private Piece[][] chessboard = new Piece[8][8];
    int x;
    int y;
    Scanner scanner = new Scanner(System.in);

    public void setPieceOnBoard(Piece piece) {
        int newPieceX = piece.getCoordinateX();
        int newPieceY = piece.getCoordinateY();
        chessboard[newPieceX][newPieceY] = piece;
    }

    public void setAllPawnsOnBoard() {
        for (int file = 0; file < 8; file++) {
            chessboard[6][file] = new Pawn(6, file, Color.WHITE);
            chessboard[1][file] = new Pawn(1, file, Color.BLACK);
        }
    }
    public Piece getPiece(int x, int y) {
        return chessboard[x][y];
    }
    public Pawn getPawnByChessMark(String stringCoordinates) {
        Piece pawn = new Pawn();
        char file = stringCoordinates.charAt(0);
        int rank = Integer.parseInt(Character.toString(stringCoordinates.charAt(1)));
        int x = pawn.setXForRank(rank);
        int y = pawn.setYForFile(file);
        return (Pawn) chessboard[x][y];
    }
    public Pawn getCurrentPawn(String currentCoordinates) {
        return getPawnByChessMark(currentCoordinates);
    }

    public void movePawn(String currentCoordinates, String requiredCoordinates) {
        while (!isSquareEmpty(requiredCoordinates)) {
            System.out.println("Pole zajete. Brak mozliwosci ruchu. Podaj oznaczenie wolnej pozycji: ");
            requiredCoordinates = scanner.nextLine();
        }
        Pawn currentPawn = getCurrentPawn(currentCoordinates);
        int currentX = currentPawn.getCoordinateX();
        int currentY = currentPawn.getCoordinateY();
        currentPawn.setCoordinates(requiredCoordinates);
        boolean isMoveValid = validateMovePawn(currentPawn, currentX);
        while (!isMoveValid) {
              System.out.println("Bledne pole docelowe. Podaj oznaczenie wlasciwego pola docelowego: ");
              requiredCoordinates = scanner.nextLine();
              currentPawn.setCoordinates(requiredCoordinates);
              isMoveValid = validateMovePawn(currentPawn, currentX);
            }
            setPieceOnBoard(currentPawn);
            currentPawn.setHasMoved(true);
            chessboard[currentX][currentY] = null;
        }
    private boolean validateMovePawn(Pawn currentPawn, int currentX) {
            int requiredX = currentPawn.getCoordinateX();
            boolean hasMoved = currentPawn.isHasMoved();
            int offset = Math.abs(currentX - requiredX);
            return (offset <= 2 && !hasMoved) || (offset == 1 && hasMoved);
        }
    public Pawn[] isEnemyInTheRangeOfPawn(Pawn currentPawn) {
        int currentX = currentPawn.getCoordinateX();
        int currentY = currentPawn.getCoordinateY();
        int checkPositionXIncrease = currentX + 1;
        int checkPositionXDecrease = currentX - 1;
        int checkPositionYIncrease = currentY + 1;
        int checkPositionYDecrease = currentY - 1;
        Pawn isEnemy[] = new Pawn[2];
        Pawn pawnToCheck;
        if (currentY == 0) {
            pawnToCheck = currentPawn.getColor() == Color.WHITE ? (Pawn) getPiece(checkPositionXDecrease, checkPositionYIncrease)
                    : (Pawn) getPiece(checkPositionXIncrease, checkPositionYIncrease);
            if (pawnToCheck != null)
                isEnemy[0] = currentPawn.getColor().equals(pawnToCheck.getColor()) ? null : pawnToCheck;
                isEnemy[1] = null;
            return isEnemy;
        } else if ((currentY == 7)) {
            pawnToCheck = currentPawn.getColor() == Color.WHITE ? (Pawn) getPiece(checkPositionXDecrease, checkPositionYDecrease)
                    : (Pawn) getPiece(checkPositionXIncrease, checkPositionYDecrease);
            if (pawnToCheck != null)
                isEnemy[0] = currentPawn.getColor().equals(pawnToCheck.getColor()) ? null : pawnToCheck;
                isEnemy[1] = null;
            return isEnemy;
        } else {
            Color currentColor = currentPawn.getColor();
            if (currentColor == Color.WHITE) {
                pawnToCheck = (Pawn) getPiece(checkPositionXDecrease, checkPositionYDecrease);
                if (pawnToCheck != null) {
                    isEnemy[0] = currentColor.equals(pawnToCheck.getColor()) ? null : pawnToCheck;
                }
                pawnToCheck = (Pawn) getPiece(checkPositionXDecrease, checkPositionYIncrease);
                if (pawnToCheck != null) {
                    isEnemy[1] = currentColor.equals(pawnToCheck.getColor()) ? null : pawnToCheck;
                }
            } else {
                pawnToCheck = (Pawn) getPiece(checkPositionXIncrease, checkPositionYIncrease);
                if (pawnToCheck != null) {
                    isEnemy[0] = currentColor.equals(pawnToCheck.getColor()) ? null : pawnToCheck;
                }
                pawnToCheck = (Pawn) getPiece(checkPositionXIncrease, checkPositionYDecrease);
                if (pawnToCheck != null) {
                    isEnemy[1] = currentColor.equals(pawnToCheck.getColor()) ? null : pawnToCheck;

                }
            }
        }
        return isEnemy;
    }
    public void checkAndHitTheEnemy(Pawn[] isEnemy, Pawn currentPawn, String currentCoordinates) {
        for (int i = 0; i < isEnemy.length; i++) {
            if (isEnemy[i] != null) {
                String stringCoordinate = isEnemy[i].getStringCoordinates();
                String question = "Mozliwosc bicia pionka na pozycji: %s%n";
                System.out.printf(question, stringCoordinate);
                System.out.println("Potwierdz pozycje do bicia np. \"f5\": / odwolaj bicie: \"n\"");
            }
        }
                String coordinatesToHit = scanner.nextLine();
                if (coordinatesToHit.equalsIgnoreCase("n")) {
                    System.out.println("Odwolanie bicia. Podaj pozycje do ruchu na wolne pole: ");
                    String requiredCoordinates = scanner.nextLine();
                    this.movePawn(currentCoordinates, requiredCoordinates);
                    return;
                }
                Pawn enemyPawn = getPawnByChessMark(coordinatesToHit);
                hitTheEnemy(currentPawn, enemyPawn);
            }
    public void hitTheEnemy(Piece ownPiece, Piece enemyPiece) {
        int xOwn = ownPiece.getCoordinateX();
        int yOwn = ownPiece.getCoordinateY();
        int xEnemy = enemyPiece.getCoordinateX();
        int yEnemy = enemyPiece.getCoordinateY();
        this.chessboard[xEnemy][yEnemy] = null;
        ownPiece.setCoordinateX(xEnemy);
        ownPiece.setCoordinateY(yEnemy);
        this.chessboard[xEnemy][yEnemy] = ownPiece;
        this.chessboard[xOwn][yOwn] = null;
    }
    public boolean isSquareEmpty(String coordinatesToCheck) {
        Piece piece = new Piece() {
            @Override
            public int setYForFile(char file) {
                return super.setYForFile(file);
            }
            @Override
            public int setXForRank(int rank) {
                return super.setXForRank(rank);
            }
        };
        char file = coordinatesToCheck.charAt(0);
        int rank = Integer.parseInt(Character.toString(coordinatesToCheck.charAt(1)));
        int x = piece.setXForRank(rank);
        int y = piece.setYForFile(file);
        return this.chessboard[x][y] == null;
    }

    public Piece[][] getChessboard() {
        return chessboard;
    }

    public void showBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(i, j) != null) {
                    System.out.println(this.getPiece(i, j));
                }
            }
        }
    }

    }


