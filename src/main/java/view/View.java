package view;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View() {
        setTitle("Compilador");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1065, 583);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

//        Console
        Console console = new Console();
        contentPane.add(console.labelConsole);
        contentPane.add(console.scrollPaneConsole);
        console.scrollPaneConsole.setViewportView(console.textAreaConsole);

//        Código
        Codigo codigo = new Codigo();
        contentPane.add(codigo.scrollPaneAreaCodigo);
        codigo.scrollPaneAreaCodigo.setViewportView(codigo.textAreaCodigo);

//        Tabela Tokens
        PilhaTokens pilhaTokens = new PilhaTokens();
        contentPane.add(pilhaTokens.scrollPanePilhaTokens);
        contentPane.add(pilhaTokens.labelPilhaTokens);

//        Buttons
        Buttons buttons = new Buttons(codigo.textAreaCodigo, pilhaTokens);
        contentPane.add(buttons.buttonSalvar);
        contentPane.add(buttons.buttonAbrirArquivo);
        contentPane.add(buttons.buttonNovoArquivo);
        contentPane.add(buttons.buttonCompilar);
        contentPane.add(buttons.buttonAddRow);
        contentPane.add(buttons.buttonRemoveRow);

    }
}
