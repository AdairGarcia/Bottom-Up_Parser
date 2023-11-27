package principal;

import scanner.Scanner;
import token.Token;
import parser.ASA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Principal {
    /*  Alumnos: García Torres Adair
     *  Campero Beleche Brandon Antonio
     *  Portela Nájera Jesús Bambino
     *  Grupo: 5CM1
     */

    static boolean existenErrores = false;

    public static void main(String[] args) throws IOException {
        ejecutarPrompt();
    }

    private static void ejecutarPrompt() throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print(">>> ");
            String linea = reader.readLine();
            if(linea == null) break; // Presionar Ctrl + D
            ejecutar(linea);
            existenErrores = false;
        }
    }

    private static void ejecutar(String source){
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        /*for(Token token : tokens){
            System.out.println(token);
        }*/

        ASA analisis = new ASA(tokens);

        if (!existenErrores){
            analisis.analizar();
        }
    }

    /*
    El método error se puede usar desde las distintas clases
    para reportar los errores:
    Interprete.error(....);
     */
    static void error(int linea, String mensaje){
        reportar(linea, "", mensaje);
    }

    static void error(int linea, String donde, String mensaje){
        reportar(linea, donde, mensaje);
    }


    private static void reportar(int linea, String donde, String mensaje){
        System.err.println(
                "[linea " + linea + "] Error " + donde + ": " + mensaje
        );
        existenErrores = true;
    }

}
