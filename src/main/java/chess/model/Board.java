package chess.model;

public class Board {
    private final Piece[][] grid;

    public Board() {this.grid = new Piece[8][8];}

    public Piece getPieceAt(Square square){
        return grid[square.rank()][square.file()];
    }
}
