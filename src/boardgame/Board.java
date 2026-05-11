package boardgame;

/**
 * Representa o tabuleiro de um jogo de tabuleiro genérico.
 *
 * Board é o contêiner central de peças. Ele mantém uma matriz bidimensional
 * de {@link Piece} e fornece acesso a qualquer casa pelo par (linha, coluna)
 * ou por um objeto {@link Position}. Por pertencer ao pacote genérico
 * {@code boardgame}, esta classe não conhece nenhuma regra do xadrez: ela
 * simplesmente armazena e disponibiliza peças em suas posições.
 *
 * A separação entre Board e as regras da partida (ChessMatch) segue o
 * princípio de responsabilidade única: Board cuida apenas da estrutura
 * física do tabuleiro, enquanto ChessMatch cuida da lógica do jogo.
 *
 * No xadrez, o tabuleiro é sempre instanciado com 8 linhas × 8 colunas.
 */
public class Board {

    /** Número de linhas do tabuleiro. */
    private int rows;

    /** Número de colunas do tabuleiro. */
    private int columns;

    /**
     * Matriz interna que armazena as peças posicionadas no tabuleiro.
     * Uma casa vazia é representada por null.
     */
    private Piece[][] pieces;

    /**
     * Cria um tabuleiro com as dimensões especificadas e todas as casas vazias.
     *
     * @param rows    número de linhas
     * @param columns número de colunas
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Retorna a peça na casa indicada pelos índices de linha e coluna.
     * Retorna null se a casa estiver vazia.
     *
     * @param row    índice da linha (0-based)
     * @param column índice da coluna (0-based)
     * @return a peça nessa posição, ou null
     */
    public Piece piece(int row, int column) {
        return pieces[row][column];
    }

    /**
     * Retorna a peça na casa indicada pelo objeto {@link Position}.
     * Conveniente para código que já trabalha com objetos Position.
     *
     * @param position posição da casa desejada
     * @return a peça nessa posição, ou null
     */
    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
}
