package chess.model;

public class Board {
    private final Piece[][] grid;

    public Board() {this.grid = new Piece[8][8];}

    public static Board createInitialBoard() {
        Board board = new Board();

        //Rank 0 -> White pieces
        board.setPieceAt(new Square(new File(0), new Rank(0)), new Piece(PieceType.ROOK, Color.WHITE));
        board.setPieceAt(new Square(new File(1), new Rank(0)), new Piece(PieceType.KNIGHT, Color.WHITE));
        board.setPieceAt(new Square(new File(2), new Rank(0)), new Piece(PieceType.BISHOP, Color.WHITE));
        board.setPieceAt(new Square(new File(3), new Rank(0)), new Piece(PieceType.QUEEN, Color.WHITE));
        board.setPieceAt(new Square(new File(4), new Rank(0)), new Piece(PieceType.KING, Color.WHITE));
        board.setPieceAt(new Square(new File(5), new Rank(0)), new Piece(PieceType.BISHOP, Color.WHITE));
        board.setPieceAt(new Square(new File(6), new Rank(0)), new Piece(PieceType.KNIGHT, Color.WHITE));
        board.setPieceAt(new Square(new File(7), new Rank(0)), new Piece(PieceType.ROOK, Color.WHITE));

        //white pawns
        for (int file=0; file < 8; file++) {
            board.setPieceAt(new Square(new File(file), new Rank(1)),  new Piece(PieceType.PAWN, Color.WHITE));
        }
        //Rank 6 -> black pieces
        board.setPieceAt(new Square(new File(0), new Rank(7)), new Piece(PieceType.ROOK, Color.BLACK));
        board.setPieceAt(new Square(new File(1), new Rank(7)), new Piece(PieceType.KNIGHT, Color.BLACK));
        board.setPieceAt(new Square(new File(2), new Rank(7)), new Piece(PieceType.BISHOP, Color.BLACK));
        board.setPieceAt(new Square(new File(3), new Rank(7)), new Piece(PieceType.QUEEN, Color.BLACK));
        board.setPieceAt(new Square(new File(4), new Rank(7)), new Piece(PieceType.KING, Color.BLACK));
        board.setPieceAt(new Square(new File(5), new Rank(7)), new Piece(PieceType.BISHOP, Color.BLACK));
        board.setPieceAt(new Square(new File(6), new Rank(7)), new Piece(PieceType.KNIGHT, Color.BLACK));
        board.setPieceAt(new Square(new File(7), new Rank(7)), new Piece(PieceType.ROOK, Color.BLACK));

        //black pawns
        for (int file=0; file < 8; file++) {
            board.setPieceAt(new Square(new File(file), new Rank(6)),  new Piece(PieceType.PAWN, Color.BLACK));
        }


        return board;
    }

    public Piece getPieceAt(Square square){
        return grid[square.rank()][square.file()];
    }
    public void setPieceAt(Square square, Piece piece){
        grid[square.rank()][square.file()] = piece;
    }
    public void removePieceAt(Square square) {
        grid[square.rank()][square.file()] = null;
    }
}
