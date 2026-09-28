package software.ulpgc.chess.model;

public class File {
    private final int value;
    public File(int file) {
        if (file<0 || file > 7){
            throw new IllegalArgumentException("position out  of range");
        }
        this.value = file;
    }
    public int toInt() {
        return value;
    }
}
