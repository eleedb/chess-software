package chess.service;

import chess.model.Board;
import chess.model.Piece;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;

public class MovementExecuter {
    public Piece applyMove ( Board board, Square squareFrom, Square squareTo){
        Piece sourcePiece = board.getPieceAt(squareFrom);
        if(sourcePiece==null){
            throw new IllegalStateException("there isn't any pieces in the origin square");
        }
        Piece capturedPiece = board.getPieceAt(squareTo);

        board.setPieceAt(squareTo, sourcePiece);
        board.removePieceAt(squareFrom);

        return capturedPiece;
    }
}
