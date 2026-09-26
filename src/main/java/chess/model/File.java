package chess.model;

public class File {
    private final int value;
    public File(int file) {
        this.value = file;
    }
    public int toInt() {
        return value;
    }
}
