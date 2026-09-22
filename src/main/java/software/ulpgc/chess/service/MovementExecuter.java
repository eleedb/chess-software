package software.ulpgc.chess.service;

import software.ulpgc.chess.model.Board;
import software.ulpgc.chess.model.Piece;
import software.ulpgc.chess.model.File;
import software.ulpgc.chess.model.Rank;

public class MovementExecuter {
    // cambio físico de las casillas
    //captura la pieza si existe en el destino y limpia el origen
    public Piece applyMove ( Board board, File fileFrom, Rank rankFrom, File fileTo, Rank rankTo){
        Piece sourcePiece = board.getPieceAt(fileFrom, rankFrom);
        if(sourcePiece==null){
            throw new IllegalStateException("no hay ninguna pieza en la posicion de origen");
        }
        Piece capturedPiece = board.getPieceAt(fileTo, rankTo);

        board.setPieceAt(fileTo, rankTo, sourcePiece);
        board.removePieceAt(fileFrom, rankFrom);

        return capturedPiece;
    }
}
