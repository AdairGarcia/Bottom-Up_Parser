package parser.grammar;

import parser.NonTerminal;
import token.TipoToken;

import java.util.ArrayList;
public class Production {
    // Similar a al Scanner, guarda las producciones de la gramatica
    // para poder comprobar si la entrada es valida
    // a partir de la pila y la entrada
    // y poder hacer las reducciones


    // Parte de la gramatica que se encuentra en el lado izquierdo
    // es decir el no terminal
    public static Object nonTerminal(int i){
        switch (i){

            // Existen algunas no terminales que tienen mas de una produccion
            case 0: return NonTerminal.Q;
            case 1,2: return NonTerminal.D;
            case 3,4: return NonTerminal.P;
            case 5,6: return NonTerminal.A;
            case 7 : return NonTerminal.A1;
            case 8,9 : return NonTerminal.A2;
            case 10,11: return NonTerminal.T;
            case 12: return NonTerminal.T1;
            case 13,14: return NonTerminal.T2;
            default:
                System.err.println("Error: Non-Terminal no encontrado");
                System.exit(1);
        }
        return null;
    }

    // Parte de la gramatica que se encuentra en el lado derecho
    // es decir, las derivaciones
    public static ArrayList<Object> derivation(int i){
        ArrayList<Object> production = new ArrayList<>();

        switch (i){
            case 0:
                // Q -> SELECT D FROM T
                production.add(NonTerminal.T);
                production.add(TipoToken.FROM);
                production.add(NonTerminal.D);
                production.add(TipoToken.SELECT);
                break;
            case 1:
                // D -> DISTINCT P
                production.add(NonTerminal.P);
                production.add(TipoToken.DISTINCT);
                break;
            case 2:
                // D -> P
                production.add(NonTerminal.P);
                break;
            case 3:
                // P -> *
                production.add(TipoToken.ASTERISCO);
                break;
            case 4:
                // P -> A
                production.add(NonTerminal.A);
                break;
            case 5:
                // A -> A,A1
                production.add(NonTerminal.A1);
                production.add(TipoToken.COMA);
                production.add(NonTerminal.A);
                break;
            case 6:
                // A -> A1
                production.add(NonTerminal.A1);
                break;
            case 7:
                // A1 -> idA2
                production.add(NonTerminal.A2);
                production.add(TipoToken.IDENTIFICADOR);
                break;
            case 8:
                // A2 -> .id
                production.add(TipoToken.IDENTIFICADOR);
                production.add(TipoToken.PUNTO);
                break;

            case 10:
                // T -> T,T1
                production.add(NonTerminal.T1);
                production.add(TipoToken.COMA);
                production.add(NonTerminal.T);
                break;
            case 11:
                // T -> T1
                production.add(NonTerminal.T1);
                break;
            case 12:
                // T1 -> idT2
                production.add(NonTerminal.T2);
                production.add(TipoToken.IDENTIFICADOR);
                break;

            case 13:
                // T2 -> id
                production.add(TipoToken.IDENTIFICADOR);
                break;

            case 9,14:
                // Producciones con epsilon
                break;

            default:
                // No se encuentra en la gramatica
                System.err.println("Error: Produccion no encontrada");
                System.exit(1);
                break;
        }
        return production;
    }
}
