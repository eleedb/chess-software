package software.ulpgc.chess.model;

import software.ulpgc.chess.service.*;

public class Main {
    public static void main(String[] args) {
        // Test/Verificación visual simple
        Board board = Board.createInitialBoard();
        ChessRulesEngine engine = new ChessRulesEngine();

// 1. Peón blanco e2 a e4 (Avanza 2 casillas desde posición inicial rank 1)
        boolean e4Valido = engine.isValidMove(board, new Move(new File(4), new Rank( 1), new File(4), new Rank( 3), Color.WHITE), Color.WHITE); // true

// 2. Intento de mover la Reina d1 a h5 directamente (bloqueada por peón e2)
        boolean reinaValida = engine.isValidMove(board, new Move(new File(3),new Rank(0), new File(7), new Rank(4), Color.WHITE), Color.WHITE); // false (isPathClear falla)
        System.out.println("e4Valido: " + e4Valido);
        System.out.println("reinaValida: " + reinaValida);
    }
}
