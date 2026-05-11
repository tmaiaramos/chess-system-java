package chess;

import boardgame.Board;
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
        initialSetup();
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

    private void initialSetup() {
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));        
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
    }
}
