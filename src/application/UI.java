package application;

import chess.ChessPiece;

/**
 * Responsável por toda a interação com o usuário via terminal (interface textual).
 *
 * UI segrega completamente a apresentação da lógica de negócio: nenhuma classe
 * do pacote {@code chess} ou {@code boardgame} precisa saber como o tabuleiro é
 * exibido. Isso torna fácil trocar a interface (ex.: trocar o terminal por uma
 * janela gráfica) sem tocar nas regras do jogo.
 *
 * Todos os métodos são estáticos porque UI não possui estado próprio — ela
 * simplesmente recebe dados e os imprime. Sendo uma classe utilitária, não faz
 * sentido instanciá-la.
 *
 * A exibição do tabuleiro segue a notação algébrica do xadrez:
 * - As linhas são numeradas de 8 (topo) a 1 (base) na borda esquerda.
 * - As colunas são identificadas pelas letras a–h na borda inferior.
 */
public class UI {

    /**
     * Imprime o tabuleiro completo no terminal, incluindo as bordas com a
     * notação algébrica (números de linha e letras de coluna).
     *
     * Cada casa é impressa por {@link #printPiece(ChessPiece)}: "-" para casa
     * vazia ou a representação textual da peça caso esteja ocupada.
     *
     * @param pieces matriz 8×8 de ChessPiece retornada por
     *               {@link chess.ChessMatch#getPieces()}
     */
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }        
        System.out.println("  a b c d e f g h");
    }

    /**
     * Imprime uma única casa do tabuleiro seguida de um espaço.
     *
     * Exibe "-" quando a casa estiver vazia (peça null) ou o resultado de
     * {@code toString()} da peça quando ela existir. O espaço após o símbolo
     * garante o alinhamento visual das colunas no terminal.
     *
     * @param piece a peça a ser impressa, ou null para casa vazia
     */
    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        } else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }

}
