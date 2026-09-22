package software.ulpgc.chess.model;

import software.ulpgc.chess.exception.RoomFullException;

public class Room {
    private final String id;
    private final String code; // Código sencillo que el usuario comparte (ej: "A7X2")
    private final String hostPlayerId;
    private String guestPlayerId;
    private RoomStatus status;
    private Game game;

    public Room(String id, String code, String hostPlayerId) {
        if (id == null || code == null || hostPlayerId == null) {
            throw new IllegalArgumentException("Los datos iniciales de la sala no pueden ser nulos");
        }
        this.id = id;
        this.code = code;
        this.hostPlayerId = hostPlayerId;
        this.status = RoomStatus.WAITING;
    }

    /**
     * Un segundo jugador se une a la sala.
     * se inicia automáticamente la partida.
     */
    public void joinPlayer(String playerId) {
        if (status != RoomStatus.WAITING) {
            throw new RoomFullException("La sala " + code + " ya está llena o ha finalizado.");
        }
        if (hostPlayerId.equals(playerId)) {
            throw new IllegalArgumentException("El creador de la sala no puede unirse como invitado.");
        }

        this.guestPlayerId = playerId;
        this.status = RoomStatus.PLAYING;

        // Crear el tablero inicial y la partida de ajedrez
        Board initialBoard = Board.createInitialBoard();
        // El host juega con Blancas, el invitado con Negras
        this.game = new Game(this.id, this.hostPlayerId, this.guestPlayerId, initialBoard);
    }

    public void finishGame() {
        this.status = RoomStatus.FINISHED;
    }

    // Getters de dominio
    public String getId() { return id; }
    public String getCode() { return code; }
    public String getHostPlayerId() { return hostPlayerId; }
    public String getGuestPlayerId() { return guestPlayerId; }
    public RoomStatus getStatus() { return status; }
    public Game getGame() { return game; }
}