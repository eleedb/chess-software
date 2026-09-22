package software.ulpgc.chess.model;

public class File {
    private final int value;
    //columna en la que te encuentras (a-h)
    public File(int file){
        if (file<0 || file > 7){
            throw new IllegalArgumentException("posicion fuera del tablero 8x8");
        }
        this.value = file;
    }

    public int toInt(){
        return value;
    }
}
