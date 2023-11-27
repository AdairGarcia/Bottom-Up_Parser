package token;
// Enumeracion de los tipos de tokens
// Para un Analizador Lexico que evaluara
// peticiones SQL simples
public enum TipoToken {

    IDENTIFICADOR,

    // Palabras reservadas
    SELECT, FROM, DISTINCT,

    // Caracteres
    COMA, PUNTO, ASTERISCO,

    // Final de cadena
    EOF
}
