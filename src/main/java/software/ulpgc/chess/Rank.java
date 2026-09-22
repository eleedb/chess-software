package software.ulpgc.chess;

public class Rank {
    //fila en la que te encuentras (1-8)
    private final int value;

    public Rank(int value) {
        if (value<0 || value > 7){
            throw new IllegalArgumentException("posicion fuera del tablero 8x8");
        }
        this.value = value;
    }

    public int toInt(){
        return value;
    }
}
