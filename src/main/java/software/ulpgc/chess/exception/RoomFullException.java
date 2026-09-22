package software.ulpgc.chess.exception;

public class RoomFullException extends RuntimeException{
    public RoomFullException(String message){
        super(message);
    }
}
