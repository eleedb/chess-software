package software.ulpgc.chess.model;

import java.util.ArrayList;
import java.util.List;
import software.ulpgc.chess.service.ChessRulesEngine;
import software.ulpgc.chess.service.MovementExecuter;
import software.ulpgc.chess.exception.*;

public class Game {
    private final String id;
    private final String whitePlayerId;
    private final String blackPlayerId;
    private final Board board;
    private Color currentTurn;
    private final List<Move> moveHistory;
    private final ChessRulesEngine rulesEngine;
    private final MovementExecuter movementExecuter;

    public Game(String id, String whitePlayerId, String blackPlayerId, Board board) {
        this.id = id;
        this.whitePlayerId = whitePlayerId;
        this.blackPlayerId = blackPlayerId;
        this.board = board;
        this.currentTurn = Color.WHITE;
        this.moveHistory = new ArrayList<>();
        this.rulesEngine = new ChessRulesEngine();
        this.movementExecuter = new MovementExecuter();
    }

    public void makeMove(Move move) {
        if (!rulesEngine.isValidMove(this.board, move, this.currentTurn)) {
            throw new InvalidMoveException("El movimiento no es válido.");
        }

        // Aplicar cambio físico al tablero
        this.movementExecuter.applyMove(board, move.fileFrom(), move.rankFrom(), move.fileTo(), move.rankTo());
        this.moveHistory.add(move);

        // Cambiar turno
        this.currentTurn = (this.currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    public String getId() { return id; }
    public Board getBoard() { return board; }
    public Color getCurrentTurn() { return currentTurn; }
    public List<Move> getMoveHistory() { return moveHistory; }
}
