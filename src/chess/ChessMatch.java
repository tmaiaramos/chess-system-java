package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/**
 * Representa e controla uma partida de xadrez.
 *
 * ChessMatch é o coração do sistema: ela é responsável por toda a lógica de
 * jogo, como inicializar o tabuleiro com as peças nas posições corretas,
 * controlar de quem é o turno, validar e executar movimentos, detectar xeque
 * e xeque-mate, entre outras regras. Ao concentrar essas responsabilidades
 * aqui, o restante do código (UI, testes) não precisa saber nada sobre as
 * regras do xadrez — basta interagir com ChessMatch.
 *
 * Internamente, ChessMatch mantém uma instância de {@link Board} (8×8) e
 * trabalha com coordenadas de matriz. Externamente, expõe os dados via
 * matrizes de {@link ChessPiece} para que a camada de apresentação possa
 * exibir o estado atual do tabuleiro sem acessar a estrutura interna.
 */
public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    /**
     * O tabuleiro físico da partida, com 8 linhas e 8 colunas, conforme
     * as regras oficiais do xadrez.
     */
        private Board board;
    

    /**
     * Inicia uma nova partida de xadrez criando um tabuleiro 8×8.
     * Futuramente, este construtor também chamará o método que posiciona
     * todas as peças nas casas iniciais.
     */
    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            currentPlayer = Color.WHITE;
        }
    }
   
    public void incrementTurn() {
        turn++;
    }
    public void decrementTurn() {
        turn--;
    }

    /**
     * Retorna o estado atual do tabuleiro como uma matriz de {@link ChessPiece}.
     *
     * Este método faz a ponte entre a camada interna (matriz de {@link boardgame.Piece})
     * e a camada de apresentação: converte cada elemento para ChessPiece e
     * retorna uma cópia da grade, de modo que a UI possa iterar e exibir as
     * peças sem ter acesso direto ao objeto Board.
     *
     * Casas vazias são representadas por null na matriz retornada.
     *
     * @return matriz 8×8 de ChessPiece refletindo o estado atual do tabuleiro
     */
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position " + position);
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }        
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the piece on source position " + position);
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position " + target);
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
