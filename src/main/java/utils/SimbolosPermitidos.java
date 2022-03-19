package utils;

import java.util.ArrayList;
import java.util.List;

public class SimbolosPermitidos {

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
}
