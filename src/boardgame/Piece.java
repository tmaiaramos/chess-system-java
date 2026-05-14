package boardgame;

/**
 * Representa uma peça genérica de jogo de tabuleiro.
 *
 * Esta classe é a base da hierarquia de peças e integra a camada genérica
 * (pacote boardgame), deliberadamente sem qualquer conhecimento das regras do
 * xadrez. O objetivo é promover reuso: qualquer jogo de tabuleiro (damas,
 * Go, etc.) poderia estender esta classe sem alterá-la.
 *
 * Cada peça conhece:
 * - O tabuleiro ao qual pertence (Board), para que possa consultar o estado
 *   do jogo ao calcular movimentos futuros.
 * - Sua posição atual (Position), que começa nula porque a peça ainda não foi
 *   colocada no tabuleiro no momento da construção.
 *
 * O atributo {@code board} é privado e exposto apenas para subclasses via
 * {@link #getBoard()}, impedindo que código externo manipule o tabuleiro
 * diretamente a partir da peça. Já {@code position} é protegido para que
 * subclasses (como ChessPiece) possam lê-la sem precisar de um getter público.
 */
public abstract class Piece {

    /** Posição atual da peça no tabuleiro; null enquanto não estiver posicionada. */
    protected Position position;

    /** Referência ao tabuleiro ao qual esta peça pertence. */
    private Board board;

    /**
     * Cria uma peça associada ao tabuleiro informado.
     * A posição inicial é null porque a peça só é colocada no tabuleiro
     * em um momento posterior (ao popular as casas).
     *
     * @param board tabuleiro ao qual a peça pertence
     */
    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    /**
     * Retorna o tabuleiro ao qual esta peça pertence.
     * Visibilidade protegida para que somente subclasses acessem o tabuleiro
     * diretamente, preservando o encapsulamento.
     *
     * @return o tabuleiro desta peça
     */
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
