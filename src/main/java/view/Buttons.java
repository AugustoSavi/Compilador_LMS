package view;

import compilador.Compilador;
import utils.ManipuladorArquivos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Buttons {
    public JButton buttonNovoArquivo;
    public JButton buttonSalvar;
    public JButton buttonCompilar;
    public JButton buttonAbrirArquivo;
    public String PATH_FILE = "";

    public Buttons(JTextArea textAreaCodigo, PilhaTokens pilhaTokens, Console console) {

        buttonAbrirArquivo = new JButton();
        buttonAbrirArquivo.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        buttonAbrirArquivo.setToolTipText("Abrir Arquivo");
        buttonAbrirArquivo.setBounds(10, 5, 70, 23);
        buttonAbrirArquivo.addActionListener(actionEvent -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jFileChooser.setCurrentDirectory(new File("."));

            try {
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    StringBuilder stringBuilder = new ManipuladorArquivos().readFile(jFileChooser.getSelectedFile().getPath());
                    String texto = stringBuilder.toString();
                    textAreaCodigo.setText(texto);
                    PATH_FILE = jFileChooser.getSelectedFile().getPath();
                }

            } catch (IOException ioException) {
                // TODO Auto-generated catch block
                ioException.printStackTrace();
            }
        });

        buttonNovoArquivo = new JButton();
        buttonNovoArquivo.setIcon(UIManager.getIcon("FileView.fileIcon"));
        buttonNovoArquivo.setToolTipText("Novo Arquivo");
        buttonNovoArquivo.setBounds(85, 5, 70, 23);
        buttonNovoArquivo.addActionListener(actionEvent -> {
            String codigoFonte = textAreaCodigo.getText();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            if (codigoFonte.isEmpty()) {
                JOptionPane.showMessageDialog(null, "código fonte vazio");

            } else {
                try {
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        new ManipuladorArquivos().writeFile(codigoFonte, fileChooser.getSelectedFile().getPath());
                        PATH_FILE = fileChooser.getSelectedFile().getPath();
                    }
                } catch (IOException ioException) {
                    // TODO Auto-generated catch block
                    ioException.printStackTrace();
                }
            }
        });

        buttonSalvar = new JButton();
        buttonSalvar.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        buttonSalvar.setToolTipText("Salvar");
        buttonSalvar.setBounds(160, 5, 70, 23);
        buttonSalvar.addActionListener(actionEvent -> {
            String codigoFonte = textAreaCodigo.getText();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            if(codigoFonte.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Código Fonte vazio");

            }else {
                try {
                    if (!PATH_FILE.isEmpty()){
                        new ManipuladorArquivos().writeFile(codigoFonte,PATH_FILE);
                    }
                    else if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        new ManipuladorArquivos().writeFile(codigoFonte,fileChooser.getSelectedFile().getPath());
                        PATH_FILE = fileChooser.getSelectedFile().getPath();
                    }
                } catch (IOException ioException) {
                    // TODO Auto-generated catch block
                    ioException.printStackTrace();
                }
            }
        });

        buttonCompilar = new JButton();
        buttonCompilar.setIcon(UIManager.getIcon("FileView.computerIcon"));
        buttonCompilar.setToolTipText("Compilar");
        buttonCompilar.setBounds(235, 5, 70, 23);
        buttonCompilar.addActionListener(actionEvent -> new Compilador(textAreaCodigo, PATH_FILE, pilhaTokens, console));
    }
}
