package software.ulpgc.chess;

public class Board {
    //cada tablero tiene 64 squares
    //contiene pieces
    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[8][8];
    }

    public static Board createInitialBoard() {
        Board board = new Board();

        //Fila 0 -> Piezas blancas
        board.setPieceAt(new File(0), new Rank(0), new Piece(PieceType.ROOK, Color.WHITE));
        board.setPieceAt(new File(1), new Rank(0), new Piece(PieceType.KNIGHT, Color.WHITE));
        board.setPieceAt(new File(2) , new Rank(0), new Piece(PieceType.BISHOP, Color.WHITE));
        board.setPieceAt(new File(3), new Rank( 0), new Piece(PieceType.QUEEN, Color.WHITE));
        board.setPieceAt(new File(4), new Rank(0), new Piece(PieceType.KING, Color.WHITE));
        board.setPieceAt(new File (5), new Rank(0), new Piece(PieceType.BISHOP, Color.WHITE));
        board.setPieceAt(new File(6), new Rank(0), new Piece(PieceType.KNIGHT, Color.WHITE));
        board.setPieceAt(new File(7), new Rank( 0), new Piece(PieceType.ROOK, Color.WHITE));

        //peones blancos
        for (int file=0; file < 8; file++) {
            board.setPieceAt(new File(file), new Rank(1),  new Piece(PieceType.PAWN, Color.WHITE) );
        }

        //Fila 6 -> Piezas negras
        board.setPieceAt(new File(0), new Rank(6), new Piece(PieceType.ROOK, Color.BLACK));
        board.setPieceAt(new File(1), new Rank(6), new Piece(PieceType.KNIGHT, Color.BLACK));
        board.setPieceAt(new File(2) , new Rank(6), new Piece(PieceType.BISHOP, Color.BLACK));
        board.setPieceAt(new File(3), new Rank( 6), new Piece(PieceType.QUEEN, Color.BLACK));
        board.setPieceAt(new File(4), new Rank(6), new Piece(PieceType.KING, Color.BLACK));
        board.setPieceAt(new File (5), new Rank(6), new Piece(PieceType.BISHOP, Color.BLACK));
        board.setPieceAt(new File(6), new Rank(6), new Piece(PieceType.KNIGHT, Color.BLACK));
        board.setPieceAt(new File(7), new Rank( 6), new Piece(PieceType.ROOK, Color.BLACK));

        //peones negros
        for (int file=0; file < 8; file++) {
            board.setPieceAt(new File(file), new Rank(7),  new Piece(PieceType.PAWN, Color.BLACK) );
        }

        return board;
    }

    public Piece getPieceAt(File file, Rank rank){
        return grid[rank.toInt()][file.toInt()];
    }

    public void setPieceAt(File file, Rank rank, Piece piece){
        grid[rank.toInt()][file.toInt()] = piece;
    }

    public void removePieceAt(File file, Rank rank) {
        grid[rank.toInt()][file.toInt()] = null;
    }

    public boolean isEmptyAt(File file, Rank rank) {
        return grid[rank.toInt()][file.toInt()] == null;
    }

    /*public void movePiece(File fileFrom, Rank rankFrom, File fileTo, Rank rankTo) {
        Piece piece = getPieceAt(fileFrom, rankFrom);
        setPieceAt(fileTo, rankTo, piece);
        setPieceAt(fileFrom, rankFrom, null);
    }*/
}
