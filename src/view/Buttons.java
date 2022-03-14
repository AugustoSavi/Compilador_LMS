package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons {
    public JButton buttonNovoArquivo;
    public JButton buttonSalvar;
    public JButton buttonCompilar;
    public JButton buttonAbrirArquivo;

    public Buttons (){

        buttonAbrirArquivo = new JButton();
        buttonAbrirArquivo.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        buttonAbrirArquivo.setToolTipText("Abrir Arquivo");
        buttonAbrirArquivo.setBounds(10, 5, 70, 23);
        buttonAbrirArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonNovoArquivo = new JButton();
        buttonNovoArquivo.setIcon(UIManager.getIcon("FileView.fileIcon"));
        buttonNovoArquivo.setToolTipText("Novo Arquivo");
        buttonNovoArquivo.setBounds(85, 5, 70, 23);
        buttonNovoArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonSalvar = new JButton();
        buttonSalvar.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        buttonSalvar.setToolTipText("Salvar");
        buttonSalvar.setBounds(160, 5, 70, 23);
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonCompilar = new JButton();
        buttonCompilar.setIcon(UIManager.getIcon("FileView.computerIcon"));
        buttonCompilar.setToolTipText("Compilar");
        buttonCompilar.setBounds(235, 5, 70, 23);
        buttonCompilar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
