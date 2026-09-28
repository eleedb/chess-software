package software.ulpgc.chess.service;

import software.ulpgc.chess.model.*;

public class ChessRulesEngine {

    public boolean isValidMove(Board board, Move move, Color turn) {
        if (move.playerColor() != turn) {
            return false;
        }

        Piece sourcePiece = board.getPieceAt(move.squareFrom());
        if (sourcePiece == null) {
            return false;
        }
        if (sourcePiece.color() != move.playerColor()) {
            return false;
        }

        Piece targetPiece = board.getPieceAt(move.squareTo());
        if (targetPiece != null && targetPiece.color() == move.playerColor()) {
            return false;
        }

        return validatePieceMovement(board, sourcePiece, move.squareFrom(), move.squareTo());
    }

    public boolean validatePieceMovement(Board board, Piece piece, Square squareFrom, Square squareTo) {
        int deltaFile = Math.abs(squareTo.file() - squareFrom.file());
        int deltaRank = Math.abs(squareTo.rank() - squareFrom.rank());

        switch (piece.type()) {
            case ROOK -> {
                //torre
                return (squareFrom.file() == squareTo.file() || squareFrom.rank() == squareTo.rank()) && isPathClear(board, squareFrom, squareTo);
            }
            case KNIGHT -> {
                //caballo
                return (deltaFile == 1 && deltaRank == 2) || (deltaFile == 2 && deltaRank == 1);
            }
            case BISHOP -> {
                //alfil
                return (deltaFile == deltaRank) && isPathClear(board, squareFrom, squareTo);
            }
            case QUEEN -> {
                //reina
                return (squareFrom.file() == squareTo.file() || squareFrom.rank() == squareTo.rank() || deltaFile == deltaRank) && isPathClear(board, squareFrom, squareTo);
            }
            case KING -> {
                //rey
                return deltaFile <= 1 && deltaRank <= 1 && (deltaFile + deltaRank > 0);
            }
            case PAWN -> {
                return validatePawnMove(board, piece, squareFrom, squareTo);
            }
        }
        return false;
    }

    private boolean validatePawnMove(Board board, Piece pawn, Square squareFrom, Square squareTo) {
        int direction = (pawn.color() == Color.WHITE) ? 1 : -1;
        int startRank = (pawn.color() == Color.WHITE) ? 1 : 6;

        int fileDiff = squareTo.file() - squareFrom.file();
        int rankDiff = squareTo.rank() - squareFrom.rank();

        Piece targetPiece = board.getPieceAt(squareTo);

        //avanza hacia delante misma columna
        if(fileDiff == 0){
            //avanza 1 casilla
            if(rankDiff == direction){
                return targetPiece == null;
            }
            //avanza 2 casillas desde pos inicial
            if(squareFrom.rank() == startRank && rankDiff == 2 * direction){
                return targetPiece == null && isPathClear(board, squareFrom, squareTo);
            }
            return false;
        }
        //captura diagonal (1 col izq/dch y 1 fila en la direccion del peon)
        if(Math.abs(fileDiff) == 1 && rankDiff == direction){
            return targetPiece != null;
        }
        return false;
    }

    public boolean isPathClear(Board board, Square squareFrom, Square squareTo) {
        int fileStep = Integer.compare(squareTo.file(), squareFrom.file()); // Returns 1, 0, or -1
        int rankStep = Integer.compare(squareTo.rank(), squareFrom.rank()); // Returns 1, 0, or -1

        int currentFile = squareFrom.file() + fileStep;
        int currentRank = squareFrom.rank() + rankStep;

        while (currentFile != squareTo.file() || currentRank != squareTo.rank()) {
            Square currentSquare = new Square(new File(currentFile), new Rank(currentRank));

            if (!board.isEmptyAt(currentSquare)) {
                return false; // Pieza bloqueando la trayectoria
            }

            currentFile += fileStep;
            currentRank += rankStep;
        }

        return true;
    }
}