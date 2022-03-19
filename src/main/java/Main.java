
import java.util.Stack;

public class Main {

    public void init() {
        //- Programa a ser analisado
        String programa
                = "PROGRAM OUTRO ; "
                + " VAR "
                + "   X , Y , Z , A : INTEGER ; "
                + "BEGIN "
                + "   X := 10 ; "
                + "   Y := 20 ; "
                + "   Z := 30 ; "
                + "   A := 50 ; "
                + "  REPEAT "
                + "    BEGIN "
                + "      WRITELN ( X , Y , Z ) ; "
                + "    END "
                + "  UNTIL X > 10 ; "
                + "END .";

        // Fase 1: Análise léxica simples onde o separador será sempre um espaço para facilitar
        Stack<String> tokens = new AnalisadorLexico().gerarTokens(programa);

        //Fase 2: Análise sintática, verificando se o código respeita a gramática
        new AnalisadorSintatico().analisar(tokens);

    }

    //executa o programa
    public static void main(String args[]) {
        new Main().init();//inicia o programa
    }

}
