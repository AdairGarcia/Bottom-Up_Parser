package parser;

import token.TipoToken;
import parser.grammar.Production;


// Programa que implementa el analizador sintáctico ascendente
// mediante el uso
public class State {
    private Object token; // Token en turno
    public State() {}

    // Funcion para realizar la accion correspondiente, ya sea desplazar o reducir
    // al estado y token en turno
    // i: indice del estado
    // t: token en turno
    public void Action(int i, Object t) {
        this.token = t;

        switch (i) {
            case 0 -> State0();
            case 1 -> State1();
            case 2 -> State2();
            case 3 -> State3();
            case 4 -> State4();
            case 5 -> State5();
            case 6 -> State6();
            case 7 -> State7();
            case 8 -> State8();
            case 9 -> State9();
            case 10 -> State10();
            case 11 -> State11();
            case 12 -> State12();
            case 13 -> State13();
            case 14 -> State14();
            case 15 -> State15();
            case 16 -> State16();
            case 17 -> State17();
            case 18 -> State18();
            case 19 -> State19();
            case 20 -> State20();
            case 21 -> State21();
            case 22 -> State22();
            case 23 -> State23();
            default -> {
                System.err.println("Error: No se hallo estado");
                System.exit(1);
            }
        }
    }

    private void Error() {
        System.out.println("Error: indice vacio");
        ASA.i = ASA.Tokens.size();
    }

    // Funcion para reducir la derivacion en un NonTerminal
    private void Reduce(int production) {
        // Por cada elemento en la derivacion, se compara con el estado
        // ya que el libro de dragon dice que debemos hacer esta comparacion

        for (Object element : Production.derivation(production)) {
            // Si el elemento es igual al estado, se hace pop
            if (element.equals(ASA.Symbols.peek())) {
                ASA.Symbols.pop();
            } else {
                System.err.println("Error: No se hallo estado");
                System.exit(1);
            }
        }
        // Se agrega el no terminal a la pila
        ASA.Symbols.push(Production.nonTerminal(production));
        // Se actualiza el input a partir del tope de la pila, es decir, el no terminal agregado
        ASA.Input = ASA.Symbols.peek();
    }

    // Funcion para desplazar el token en turno
    private void Displace() {
        ASA.Symbols.push(token);// Se agrega el token a la pila
        ASA.i++; // Se incrementa el indice de la entrada
        ASA.Input = ASA.Tokens.get(ASA.i).getTipo(); // Se actualiza el input
    }

    /* ESTADOS */

    // Funciones para cada estado del analizador sintactico ascendente
    private void State0() {
        if (token instanceof TipoToken) {
            if (token == TipoToken.SELECT) {
                ASA.Pila.push(2);
                Displace();
            } else {
                Error();
            }
        } else if (token.equals(NonTerminal.Q)) {
            ASA.Pila.push(1);
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        }
    }

    private void State1() {
        if (token.equals(TipoToken.EOF)) {
            System.out.print("Consulta correcta\n");
            ASA.i = ASA.Tokens.size();
        } else {
            Error();
        }
    }

    private void State2() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.D)) {
                ASA.Pila.push(3);
            } else if (token.equals(NonTerminal.P)) {
                ASA.Pila.push(5);
            } else if (token.equals(NonTerminal.A)) {
                ASA.Pila.push(7);
            } else if (token.equals(NonTerminal.A1)) {
                ASA.Pila.push(8);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.DISTINCT)) {
                ASA.Pila.push(4);
                Displace();
            } else if (token.equals(TipoToken.ASTERISCO)) {
                ASA.Pila.push(6);
                Displace();
            } else if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(9);
                Displace();
            } else {
                Error();
            }
        }
    }

    private void State3() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM)) {
                ASA.Pila.push(10);
                Displace();
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }

    private void State4() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.P)) {
                ASA.Pila.push(11);
            } else if (token.equals(NonTerminal.A)) {
                ASA.Pila.push(7);
            } else if (token.equals(NonTerminal.A1)) {
                ASA.Pila.push(8);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(9);
                Displace();
            } else if (token.equals(TipoToken.ASTERISCO)) {
                ASA.Pila.push(6);
                Displace();
            } else {
                Error();
            }
        }
    }

    private void State5() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM)) {
                Reduce(2);
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State6() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM)) {
                Reduce(3);
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State7() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM)) {
                Reduce(4);
            }
            else if(token.equals(TipoToken.COMA)) {
                ASA.Pila.push(12);
                Displace();
            }
            else{
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State8() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM) || token.equals(TipoToken.COMA)) {
                Reduce(6);
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State9() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.A2)) {
                ASA.Pila.push(13);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.PUNTO)) {
                ASA.Pila.push(14);
                Displace();
            } else if (token.equals(TipoToken.FROM) || token.equals(TipoToken.COMA)) {
                Reduce(9);
            } else {
                Error();
            }
        }
    }

    private void State10() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.T)) {
                ASA.Pila.push(16);
            } else if (token.equals(NonTerminal.T1)) {
                ASA.Pila.push(17);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(18);
                Displace();
            } else {
                Error();
            }
        }
    }

    private void State11() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM)) {
                Reduce(1);
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }

    private void State12() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.A1)) {
                ASA.Pila.push(19);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(9);
                Displace();
            } else {
                Error();
            }
        }
    }

    private void State13() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM) || token.equals(TipoToken.COMA)) {
                Reduce(7);
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State14() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(15);
                Displace();
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }

    private void State15() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM) || token.equals(TipoToken.COMA)) {
                Reduce(8);
            } else {
                Error();
            }
        } else ASA.Pila.pop();

    }

    private void State16() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.EOF)) {
                Reduce(0);
            } else if (token.equals(TipoToken.COMA)) {
                ASA.Pila.push(20);
                Displace();
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State17() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.EOF) || token.equals(TipoToken.COMA)) {
                Reduce(11);
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }


    private void State18() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.T2)) {
                ASA.Pila.push(23);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(22);
                Displace();
            } else if (token.equals(TipoToken.EOF) || token.equals(TipoToken.COMA)) {
                Reduce(14);
            } else {
                Error();
            }
        }
    }

    private void State19() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.FROM) || token.equals(TipoToken.COMA) ) {
                Reduce(5);
            } else {
                Error();
            }
        } else {
            ASA.Pila.pop();
        }
    }

    private void State20() {
        if (token instanceof NonTerminal) {
            if (token.equals(NonTerminal.T1)) {
                ASA.Pila.push(21);
            } else {
                ASA.Pila.pop();
                return;
            }
            ASA.Input = ASA.Tokens.get(ASA.i).getTipo();
        } else if (token instanceof TipoToken) {
            if (token.equals(TipoToken.IDENTIFICADOR)) {
                ASA.Pila.push(18);
                Displace();
            } else {
                Error();
            }
        }
    }

    private void State21() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.EOF) || token.equals(TipoToken.COMA)) {
                Reduce(10);
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }

    private void State22() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.EOF) || token.equals(TipoToken.COMA)) {
                Reduce(13);
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }

    private void State23() {
        if (token instanceof TipoToken) {
            if (token.equals(TipoToken.EOF) || token.equals(TipoToken.COMA)) {
                Reduce(12);
            } else {
                Error();
            }
        } else ASA.Pila.pop();
    }



}
