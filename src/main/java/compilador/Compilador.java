package compilador;

import analisadorLexico.AnalisadorLexico;
import analisadorSintatico.AnalisadorSintatico;
import model.*;
import utils.ManipuladorArquivos;
import view.Console;
import view.PilhaTokens;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class Compilador {

    public Compilador(JTextArea textAreaCodigo, String PATH_FILE, PilhaTokens pilhaTokens, Console console) {
        if (textAreaCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código Fonte vazio");
        } else if (PATH_FILE.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Salve o arquivo antes de compilar");
        } else {
            pilhaTokens.modelToken.setRowCount(0);

            Queue<Linha> linhas = new ManipuladorArquivos().readerLineByLine(PATH_FILE);
            LexicoReturn lexicoReturn = new AnalisadorLexico().analisadorLexico(linhas);

            Queue<Token> tokensAnaliseSintatica = new LinkedList<>(lexicoReturn.tokens);
            SintaticoReturn sintaticoReturn = new AnalisadorSintatico().analiseSintatica(tokensAnaliseSintatica);

            if (!lexicoReturn.tokens.isEmpty()) {
                for (Token token : lexicoReturn.tokens) {
                    pilhaTokens.addRow(token);
                }
            }

            if (!lexicoReturn.notificacaoConsoles.isEmpty()) {
                console.textAreaConsole.setText("");
                for (NotificacaoConsole notificacaoConsole : lexicoReturn.notificacaoConsoles) {
                    console.textAreaConsole.append(notificacaoConsole.getMensagem() + "\n");
                }
            }

            if (!sintaticoReturn.getNotificacaoConsoles().isEmpty()) {
                for (NotificacaoConsole notificacaoConsole : sintaticoReturn.getNotificacaoConsoles()) {
                    console.textAreaConsole.append(notificacaoConsole.getMensagem() + "\n");
                }
            }
        }
    }
}
