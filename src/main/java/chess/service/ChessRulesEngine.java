package chess.service;

import chess.model.*;

public class ChessRulesEngine {

    public boolean isValidMove(Board board, Move move, Color turn){
        if(move.playerColor() != turn){
            return false;
        }

        Piece sourcePiece = board.getPieceAt(move.squareFrom());
        if(sourcePiece == null){
            return false;
        }
        if (sourcePiece.color() != move.playerColor()){
            return false;
        }

        Piece targetPiece = board.getPieceAt(move.squareTo());
        if(targetPiece != null && targetPiece.color() == move.playerColor()){
            return false;
        }

        return validatePieceMovement(board, sourcePiece, move.squareFrom(), move.squareTo());
    }

    public boolean validatePieceMovement(Board board, Piece piece, Square squareFrom, Square squareTo){
        return false;
    }
}
