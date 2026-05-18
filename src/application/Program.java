package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;

/**
 * Ponto de entrada da aplicação do jogo de xadrez.
 *
 * Program contém apenas o método {@code main} e tem a responsabilidade de
 * orquestrar os dois grandes componentes da aplicação: a lógica da partida
 * ({@link ChessMatch}) e a interface com o usuário ({@link UI}). Mantendo essa
 * classe enxuta, garantimos que nenhuma regra de negócio ou lógica de exibição
 * vaze para o ponto de entrada — ele apenas inicia a partida e delega as
 * responsabilidades para as classes certas.
 *
 * Fluxo atual (fase inicial do projeto):
 * 1. Cria uma nova partida (ChessMatch), que inicializa o tabuleiro 8×8.
 * 2. Solicita o estado das peças à partida via {@code getPieces()}.
 * 3. Passa a matriz de peças para UI imprimir o tabuleiro no terminal.
 */
public class Program {

    /**
     * Método principal: inicia a partida e exibe o tabuleiro inicial.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {        
        
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch(); 
        List<ChessPiece> captured = new ArrayList<>();

        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);                

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
