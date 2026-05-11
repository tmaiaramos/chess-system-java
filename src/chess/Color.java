package chess;

/**
 * Define as duas cores possíveis de uma peça de xadrez.
 *
 * O xadrez é jogado por dois adversários identificados pelas cores branca e
 * preta. Usar um enum (em vez de, por exemplo, uma String ou um int) garante
 * que nenhum valor inválido seja atribuído à cor de uma peça em tempo de
 * compilação, tornando o código mais seguro e legível.
 *
 * Color também é utilizada para determinar de qual jogador é o turno atual
 * em {@link ChessMatch} e para diferenciar as peças adversárias ao validar
 * capturas.
 */
public enum Color {

    /** Representa as peças do jogador que joga com as peças pretas. */
    BLACK,

    /** Representa as peças do jogador que joga com as peças brancas. */
    WHITE
}
