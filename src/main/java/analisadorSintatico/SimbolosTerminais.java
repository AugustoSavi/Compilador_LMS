package analisadorSintatico;

import java.util.HashMap;
import java.util.Map;

public class SimbolosTerminais {

    private final Map<String, Integer> simbolosTerminais = new HashMap<>();

    public SimbolosTerminais() {
        this.iniciaHashMapTerminais();
    }


    public Integer getTerminal(String terminal) {
        if(simbolosTerminais.containsKey(terminal)) {
            return simbolosTerminais.get(terminal);
        }
        return 0;
    }

    public Boolean containsKey(String key) {
        return simbolosTerminais.containsKey(key);
    }

    private void iniciaHashMapTerminais() {
        simbolosTerminais.put("IDENTIFICADOR", 25);
        simbolosTerminais.put("INTEIRO", 26);
        simbolosTerminais.put("LITERAL", 48);
        simbolosTerminais.put("+", 30);
        simbolosTerminais.put("-", 31);
        simbolosTerminais.put("*", 32);
        simbolosTerminais.put("/", 33);
        simbolosTerminais.put("=", 40);
        simbolosTerminais.put(",", 46);
        simbolosTerminais.put(";", 47);
        simbolosTerminais.put("$", 51);
        simbolosTerminais.put("[", 34);
        simbolosTerminais.put("]", 35);
        simbolosTerminais.put("(", 36);
        simbolosTerminais.put(")", 37);
        simbolosTerminais.put(":", 39);
        simbolosTerminais.put(">", 41);
        simbolosTerminais.put("<", 43);
        simbolosTerminais.put(".", 49);
        simbolosTerminais.put(">=", 42);
        simbolosTerminais.put("<=", 44);
        simbolosTerminais.put("<>", 45);
        simbolosTerminais.put(":=", 38);
        simbolosTerminais.put("..", 50);
        simbolosTerminais.put("PROGRAM", 1);
        simbolosTerminais.put("LABEL", 2);
        simbolosTerminais.put("CONST", 3);
        simbolosTerminais.put("VAR", 4);
        simbolosTerminais.put("PROCEDURE", 5);
        simbolosTerminais.put("BEGIN", 6);
        simbolosTerminais.put("END", 7);
        simbolosTerminais.put("INTEGER", 8);
        simbolosTerminais.put("ARRAY", 9);
        simbolosTerminais.put("OF", 10);
        simbolosTerminais.put("CALL", 11);
        simbolosTerminais.put("GOTO", 12);
        simbolosTerminais.put("IF", 13);
        simbolosTerminais.put("THEN", 14);
        simbolosTerminais.put("ELSE", 15);
        simbolosTerminais.put("WHILE", 16);
        simbolosTerminais.put("DO", 17);
        simbolosTerminais.put("REPEAT", 18);
        simbolosTerminais.put("UNTIL", 19);
        simbolosTerminais.put("READLN", 20);
        simbolosTerminais.put("WRITELN", 21);
        simbolosTerminais.put("OR", 22);
        simbolosTerminais.put("AND", 23);
        simbolosTerminais.put("NOT", 24);
        simbolosTerminais.put("FOR", 27);
        simbolosTerminais.put("TO", 28);
        simbolosTerminais.put("CASE", 29);

    }
}
