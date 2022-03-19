package compilador;

import model.Linha;
import utils.ManipuladorArquivos;
import view.PilhaTokens;

import javax.swing.*;
import java.util.Queue;

public class Compilador {

    public Compilador(JTextArea textAreaCodigo, String PATH_FILE, PilhaTokens pilhaTokens){
        if(textAreaCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Código Fonte vazio");

        }
        else if (PATH_FILE.isEmpty()){
            JOptionPane.showMessageDialog(null,"Salve o arquivo antes de compilar");
        }
        else {
            Queue<Linha> linhas = new ManipuladorArquivos().readerLineByLine(PATH_FILE);

            if(!linhas.isEmpty()) {
                for (Linha linha : linhas) {
                    pilhaTokens.addRow(linha);
                }
            }
        }
    }
}
