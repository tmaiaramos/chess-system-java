package boardgame;

/**
 * Representa uma posição no tabuleiro por meio de coordenadas de linha e coluna.
 *
 * Esta classe pertence à camada genérica de jogos de tabuleiro e é intencionalmente
 * independente das regras do xadrez. Sua responsabilidade é apenas guardar e expor
 * as coordenadas (row, column) que identificam uma casa do tabuleiro na matriz
 * interna de peças. Ao isolar esse conceito em uma classe própria, evita-se o
 * espalhamento de pares de inteiros pelo código e torna-se possível passar ou
 * retornar uma posição como um único objeto.
 */
public class Position {

    /** Índice da linha na matriz do tabuleiro (0-based). */
    private int row;

    /** Índice da coluna na matriz do tabuleiro (0-based). */
    private int column;

    /**
     * Cria uma posição com linha e coluna especificadas.
     *
     * @param row    índice da linha (0-based)
     * @param column índice da coluna (0-based)
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {   
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }    

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Retorna a posição no formato "row, column" para facilitar depuração.
     */
    @Override
    public String toString() {
        return row + ", " + column;
    }

    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
