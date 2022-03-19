package compilador;

import model.Linha;
import model.Token;
import utils.Simbolos;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AnalisadorLexico {
    private static int LITERAL = 48;

    public Queue<Token> analisadorLexico(Queue<Linha> linhas){
        Linha line = linhas.remove();
        Simbolos simbolos = new Simbolos();
        Map<String,Integer> gramaticaPermitida = simbolos.gramaticaPermitida();
        Queue<Token> tokens = new LinkedList<>();

        while (line != null) {
            for (String palavra: line.getLinha().split(" ")){
                String palavraTrimUpperCase = palavra.trim().toUpperCase();

                if (!palavraTrimUpperCase.isEmpty()) {
                    System.out.println(line.getNumeroLinha() + ":->" + palavraTrimUpperCase + ":->" + gramaticaPermitida.get(palavraTrimUpperCase));

                    if (gramaticaPermitida.containsKey(palavraTrimUpperCase)) {
                        tokens.add(new Token(gramaticaPermitida.get(palavraTrimUpperCase), line.getNumeroLinha(), palavra));
                    }
                    else if (simbolos.isLiteral(palavra)){
                        tokens.add(new Token(LITERAL, line.getNumeroLinha(), palavra));
                    }
                    else {
                       String[] characteres = palavra.split("");

                    }
                }
            }
            if(!linhas.isEmpty()) {
                line = linhas.remove();
            } else {
                break;
            }
        }

        return tokens;
    }
}
