package chess.model;

public class Square {
    private final Rank rank;
    private final File file;

    public Square(File file, Rank rank){
        this.rank = rank;
        this.file = file;
    }

    public int rank() {return rank.toInt();}
    public int file() {return file.toInt();}
}
