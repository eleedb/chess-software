package chess.model;

public class Rank {
    private final int value;

    public Rank(int value){
        this.value = value;
    }

    public int toInt(){
        return value;
    }
}
