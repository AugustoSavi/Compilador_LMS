package compilador;

import model.Linha;

import java.util.Queue;

public class AnalisadorLexico {

    public void analisadorLexico(Queue<Linha> linhas){
        Linha line = linhas.remove();

        while (line != null) {
            line = linhas.remove();
            System.out.println();
        }
    }
}
