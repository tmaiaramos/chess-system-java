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
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
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
        if (!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
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
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position);
        }        
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        if (piece(position) == null) {
            return null;
        }
        Piece aux = piece(position);
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}
