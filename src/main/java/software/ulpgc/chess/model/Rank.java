package software.ulpgc.chess.model;

public class Rank {
    private final int value;

    public Rank(int value){
        if (value<0 || value > 7){
            throw new IllegalArgumentException("position out of range");
        }
        this.value = value;
    }

    public int toInt(){
        return value;
    }
}
