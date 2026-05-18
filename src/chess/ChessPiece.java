package chess;

import boardgame.Piece;
import boardgame.Position;
import boardgame.Board;

/**
 * Representa uma peça de xadrez, estendendo a peça genérica com atributos
 * específicos do xadrez.
 *
 * A camada genérica ({@link boardgame.Piece}) sabe apenas que uma peça existe
 * e está em alguma posição de um tabuleiro. ChessPiece acrescenta o conceito
 * de {@link Color}, que é exclusivo do xadrez e determina a qual jogador a
 * peça pertence.
 *
 * Esta classe também serve como superclasse para todas as peças concretas do
 * xadrez (Rei, Rainha, Torre, Bispo, Cavalo, Peão). Centralizar a cor aqui
 * evita duplicação em cada peça concreta e permite que a lógica de validação
 * (ex.: verificar se uma captura é válida comparando cores) seja escrita uma
 * única vez nos métodos herdados.
 */
public abstract class ChessPiece extends Piece {

    /** Cor desta peça (BLACK ou WHITE), indicando a qual jogador ela pertence. */
    private Color color;

    /**
     * Cria uma peça de xadrez associada ao tabuleiro e com a cor informados.
     *
     * @param board tabuleiro ao qual a peça pertence
     * @param color cor da peça (BLACK ou WHITE)
     */
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    /**
     * Retorna a cor desta peça.
     *
     * @return BLACK ou WHITE
     */
    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    public boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }
}
