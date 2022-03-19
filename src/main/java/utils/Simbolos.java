package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simbolos {
    public static final char ASPAS_SIMPLES = '\'';

    public boolean isLiteral(String palavra) {
        String conteudo = palavra.trim();
        char posicaoInicial = conteudo.charAt(0);
        char posicaoFinal = conteudo.charAt(conteudo.length() - 1);
        return posicaoInicial == ASPAS_SIMPLES && posicaoFinal == ASPAS_SIMPLES;
    }

    public List<String> operadoresPermitidos() {
        List<String> operadores = new ArrayList<>();
        operadores.add(" ");
        operadores.add("'");
        operadores.add("\n");
        operadores.add("(*");
        operadores.add("*)");
        operadores.add(";");
        operadores.add(":");
        operadores.add("+");
        operadores.add("-");
        operadores.add("*");
        operadores.add("/");
        operadores.add("(");
        operadores.add(")");
        operadores.add(">");
        operadores.add("<");
        operadores.add("<>");
        operadores.add(">=");
        operadores.add("<=");
        operadores.add(",");
        operadores.add(".");
        operadores.add("..");
        operadores.add("[");
        operadores.add("]");
        return operadores;
    }

    public Map<String,Integer> gramaticaPermitida() {
        Map<String,Integer> simbolos = new HashMap<String,Integer>();
        simbolos.put( "PROGRAM", 1);
        simbolos.put( "LABEL", 2);
        simbolos.put( "CONST", 3);
        simbolos.put( "PROCEDURE", 5);
        simbolos.put( "VAR", 4);
        simbolos.put( "BEGIN", 6);
        simbolos.put( "END", 7);
        simbolos.put( "INTEGER", 8);
        simbolos.put( "ARRAY", 9);
        simbolos.put( "OF", 10);
        simbolos.put( "CALL", 11);
        simbolos.put( "GOTO", 12);
        simbolos.put( "IF", 13);
        simbolos.put( "THEN", 14);
        simbolos.put( "ELSE", 15);
        simbolos.put( "WHILE", 16);
        simbolos.put( "DO", 17);
        simbolos.put( "REPEAT", 18);
        simbolos.put( "UNTIL", 19);
        simbolos.put( "READLN", 20);
        simbolos.put( "WRITELN", 21);
        simbolos.put( "OR", 22);
        simbolos.put( "AND", 23);
        simbolos.put( "NOT", 24);
        simbolos.put( "IDENTIFICADOR", 25);
        simbolos.put( "INTEIRO", 26);
        simbolos.put( "FOR", 27);
        simbolos.put( "TO", 28);
        simbolos.put( "CASE", 29);
        simbolos.put( "+", 30);
        simbolos.put( "-", 31);
        simbolos.put( "*", 32);
        simbolos.put( "/", 33);
        simbolos.put( "[", 34);
        simbolos.put( "]", 35);
        simbolos.put( "(", 36);
        simbolos.put( ")", 37);
        simbolos.put( ":=", 38);
        simbolos.put( ":", 39);
        simbolos.put( "=", 40);
        simbolos.put( ">", 41);
        simbolos.put( ">=", 42);
        simbolos.put( "<", 43);
        simbolos.put( "<=", 44);
        simbolos.put( "<>", 45);
        simbolos.put( ",", 46);
        simbolos.put( ";", 47);
        simbolos.put( "LITERAL", 48);
        simbolos.put( ".", 49);
        simbolos.put( "..", 50);
        simbolos.put( "$", 51);

        return simbolos;
    }
}
