package token;

public class Token {
    final private TipoToken tipo;
    final String lexema;
    int posicion, linea, col;


    public Token(TipoToken tipo, String lexema, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = posicion;
    }

    public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = 0;
    }

    public Token(TipoToken tipo, String lexema, Object literal, int linea, int col) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if(this.tipo == ((Token)o).tipo){
            return true;
        }

        return false;
    }

    public String toString(){
        return tipo + " " + lexema + " ";
    }

    public TipoToken getTipo() {
        return this.tipo;
    }


}
