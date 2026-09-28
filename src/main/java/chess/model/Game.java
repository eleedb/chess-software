package chess.model;

import java.util.ArrayList;
import java.util.List;
import chess.service.ChessRulesEngine;
import chess.service.MovementExecuter;

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
}
