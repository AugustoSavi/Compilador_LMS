package compilador;

import model.Linha;

import java.util.Queue;

public class AnalisadorLexico {

    public void analisadorLexico(Queue<Linha> linhas){
        Linha line = linhas.remove();

        while (line != null) {
            for (String palavra: line.getLinha().split(" ")){
                if (!palavra.trim().isEmpty()) {
                    System.out.println(line.getNumeroLinha() + ":->" + palavra.trim());
                }
            }
            if(!linhas.isEmpty()) {
                line = linhas.remove();
            } else {
                break;
            }
        }
    }
}
