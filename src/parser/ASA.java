package parser;

import token.TipoToken;
import token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ASA {
    public static boolean HayErrores;
    protected static List<Token> Tokens;
    protected static Stack<Object> Symbols; // Pila de simbolos
    protected static Stack<Integer> Pila; // Pila de estados
    protected static int i = 0;
    protected static Object Input; // Token en turno

    private ASA(){
        Tokens = new ArrayList<>();
        Pila = new Stack<>();
        Symbols = new Stack<>();
        i = 0;
        HayErrores = false;

        // Estado inicial del analizador ascendente LR(1)
        Symbols.push(TipoToken.EOF);
        Pila.push(0);
    }

    public ASA(List<Token> tokens) {
        this();
        Tokens = tokens;
        Input = Tokens.get(i).getTipo();
    }

    public void analizar() {
        State estados = new State();

        // Mientras no se haya llegado al final de la entrada (Lista de tokens), de acuerdo con el algoritmo
        // del libro, se debe de seguir analizando
        while (i != Tokens.size() ){
            estados.Action(Pila.peek(), Input);
        }
    }

}
