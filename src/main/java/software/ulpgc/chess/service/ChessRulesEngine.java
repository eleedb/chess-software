package software.ulpgc.chess.service;

import software.ulpgc.chess.model.*;

public class ChessRulesEngine {

    public boolean isValidMove(Board board, Move move, Color turn){
        if(move.playerColor() != turn){
            return false;
        }

        Piece sourcePiece = board.getPieceAt(move.fileFrom(), move.rankFrom());
        if(sourcePiece == null){
            return false;
        }
        if (sourcePiece.color() != move.playerColor()){
            return false;
        }

        Piece targetPiece = board.getPieceAt(move.fileTo(), move.rankTo());
        if(targetPiece != null && targetPiece.color() == move.playerColor()){
            return false;
        }

        return validatePieceMovement(board, sourcePiece, move.fileFrom(), move.rankFrom(), move.fileTo(), move.rankTo());
    }

    public boolean validatePieceMovement(Board board, Piece piece, File fileFrom, Rank rankFrom, File fileTo, Rank rankTo){
        int deltaFile = Math.abs(fileTo.toInt() - fileFrom.toInt());
        int deltaRank = Math.abs(rankTo.toInt() - rankFrom.toInt());

        switch(piece.type()){
            case ROOK -> {
                //torre
                return (fileFrom == fileTo || rankFrom == rankTo) && isPathClear(board, fileFrom, rankFrom, fileTo, rankTo);
            }
            case KNIGHT -> {
                //caballo
                return (deltaFile == 1 && deltaRank == 2) || (deltaFile == 2 && deltaRank == 1);
            }
            case BISHOP -> {
                //alfil
                return (deltaFile == deltaRank) && isPathClear(board, fileFrom, rankFrom, fileTo, rankTo);
            }
            case QUEEN -> {
                //reina
                return (fileFrom == fileTo || rankFrom == rankTo || deltaFile == deltaRank) && isPathClear(board, fileFrom, rankFrom, fileTo, rankTo);
            }
            case KING -> {
                //rey
                return deltaFile <= 1 && deltaRank <= 1  && (deltaFile + deltaRank > 0);
            }
            case PAWN -> {
                return validatePawnMove(board, piece, fileFrom, rankFrom, fileTo, rankTo);
            }
        }
        return false;
    }

    private boolean validatePawnMove(Board board, Piece pawn, File fileFrom, Rank rankFrom, File fileTo, Rank rankTo){
        int direction = (pawn.color() == Color.WHITE) ? 1 : -1;
        int startRank = (pawn.color() == Color.WHITE) ? 1 : 6;

        int fileDiff = fileTo.toInt() - fileFrom.toInt();
        int rankDiff = rankTo.toInt() - rankFrom.toInt();

        Piece targetPiece = board.getPieceAt(fileTo, rankTo);

        //avanza hacia delante misma columna
        if(fileDiff == 0){
            //avanza 1 casilla
            if(rankDiff == direction){
                return targetPiece == null;
            }
            //avanza 2 casillas desde pos inicial
            if(rankFrom.toInt() == startRank && rankDiff == 2 * direction){
                return targetPiece == null && isPathClear(board, fileFrom, rankFrom, fileTo, rankTo);
            }
            return false;
        }
        //captura diagonal (1 col izq/dch y 1 fila en la direccion del peon)
        if(Math.abs(fileDiff) == 1 && rankDiff == direction){
            return targetPiece != null;
        }
        return false;
    }

    public boolean isPathClear(Board board, File fileFrom,  Rank rankFrom, File fileTo, Rank rankTo){
        int fileStep = Integer.compare(fileTo.toInt(), fileFrom.toInt()); // Returns 1, 0, or -1
        int rankStep = Integer.compare(rankTo.toInt(), rankFrom.toInt()); // Returns 1, 0, or -1

        int currentFileValue = fileFrom.toInt() + fileStep;
        int currentRankValue= rankFrom.toInt() + rankStep;

        while (currentFileValue != fileTo.toInt() || currentRankValue != rankTo.toInt()) {
            File currentFile = new File(currentFileValue);
            Rank currentRank = new Rank(currentRankValue);

            if (!board.isEmptyAt(currentFile, currentRank)) {
                return false; // Pieza bloqueando la trayectoria
            }

            currentFileValue += fileStep;
            currentRankValue += rankStep;
        }

        return true;
    }
}
